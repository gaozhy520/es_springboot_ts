package com.baizhi.dao.es;

import com.baizhi.entity.Poetry;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

public class CustomPoetryRepositoryImpl implements CustomPoetryRepository {
    @Autowired
    private ElasticsearchTemplate template;

    @Override
    public Map<String,Object> findByKeyWord(String keyword, Integer nowPage, Integer pageSize) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQuery()
                        .should(matchQuery("content", keyword))
                        .should(matchQuery("author", keyword)))
                .withHighlightFields(new HighlightBuilder.Field("content")
                        , new HighlightBuilder.Field("author"))
                .withPageable(new PageRequest(nowPage - 1, pageSize)) // 封装分页信息
                .build();

        AggregatedPage<Poetry> aggregatedPage = template.queryForPage(searchQuery, Poetry.class, new SearchResultMapper() {
            /**
             * 结果映射到实体类
             * @param searchResponse
             * @param aClass
             * @param pageable
             * @param <T>
             * @return
             */
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
                ArrayList<Poetry> poetries = new ArrayList<>();
                Poetry poetry = null;
                // 检索命中集合对象
                SearchHits searchHits = searchResponse.getHits();
                for (SearchHit searchHit : searchHits) {
                    poetry = new Poetry();
                    // 封装id
                    poetry.setId(Integer.parseInt(searchHit.getId()));
                    Map<String, Object> result = searchHit.getSourceAsMap();

                    //result.forEach((k,v)-> {
                    //    System.out.println(k + " | " +v);
                    //});

                    poetry.setTitle(result.get("title").toString());

                    // 将高亮结果封装到address属性中
                    if (searchHit.getHighlightFields().get("content") == null) {
                        poetry.setContent(result.get("content").toString());
                    } else {
                        poetry.setContent(searchHit.getHighlightFields().get("content").getFragments()[0].toString());
                    }

                    // System.out.println(searchHit.getHighlightFields().get("author") );
                    if (searchHit.getHighlightFields().get("author") == null) {
                        poetry.setAuthor(result.get("author").toString());
                    } else {
                        poetry.setAuthor(searchHit.getHighlightFields().get("author").getFragments()[0].toString());
                    }
                    poetries.add(poetry);
                }

                return new AggregatedPageImpl<T>((List<T>) poetries,pageable,searchHits.getTotalHits());
            }
        });

        long pageCount = aggregatedPage.getTotalPages();
        List<Poetry> poetries = aggregatedPage.getContent();
        HashMap<String, Object> map = new HashMap<>();
        map.put("pageCount",pageCount);
        map.put("poetries",poetries);
        return map;
    }
}
