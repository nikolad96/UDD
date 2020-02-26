package udd.server.search;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import udd.server.dto.MoreLikeThisDTO;
import udd.server.dto.MultiFieldQueryDTO;
import udd.server.dto.QueryDTO;
import udd.server.model.RadSearch;
import udd.server.model.RecenzentSearch;
import udd.server.repository.elastic.SearchRadRepository;
import udd.server.repository.elastic.SearchRecenzentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearchService {

    @Autowired
    ElasticsearchOperations elasticsearchOperations;

    @Autowired
    SearchRadRepository searchRadRepository;

    @Autowired
    SearchRecenzentRepository searchRecenzentRepository;

    public List<RadSearch> search(QueryDTO queryDTO){
        SearchQueryBuilder searchQueryBuilder = new SearchQueryBuilder();
//        List<RadSearch> radSearches = elasticsearchOperations.queryForList(searchQueryBuilder.createQuery(queryDTO),RadSearch.class);



        Page<RadSearch> sampleEntities = elasticsearchOperations.queryForPage(searchQueryBuilder.createQuery(queryDTO), RadSearch.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
                List<RadSearch> chunk = new ArrayList<RadSearch>();
                for (SearchHit searchHit : response.getHits()) {
                    if (response.getHits().getHits().length <= 0) {
                        return null;
                    }
                    Map<String, Object> source = searchHit.getSourceAsMap();
                    RadSearch radSearch = new RadSearch();
                    radSearch.setId(searchHit.getId());
                    radSearch.setKljucne((String) source.get("kljucne"));

                    radSearch.setText((String) source.get("text"));
                    radSearch.setAbstrakt((String) source.get("abstrakt"));
                    radSearch.setAutor((String) source.get("autor"));
                    radSearch.setNaziv((String) source.get("naziv"));
                    radSearch.setOblast((String) source.get("oblast"));


                    radSearch.setHighlight(searchHit.getHighlightFields().get(queryDTO.getPolje()).fragments()[0].toString());
                    chunk.add(radSearch);
                }
                if (chunk.size() > 0) {
                    return new AggregatedPageImpl(chunk);
                }
                return null;
            }

            @Override
            public <T> T mapSearchHit(SearchHit searchHit, Class<T> aClass) {
                return null;
            }
        });




        return sampleEntities.getContent();
    }

    public List<RadSearch> searchMulti(MultiFieldQueryDTO multiFieldQueryDTO){
        SearchQueryBuilder searchQueryBuilder = new SearchQueryBuilder();
        List<RadSearch> radSearches = elasticsearchOperations.queryForList(searchQueryBuilder.createMultiFieldQuery(multiFieldQueryDTO),RadSearch.class);
        return radSearches;
    }

    public List<RadSearch> searchMore(MoreLikeThisDTO moreLikeThisDTO){
        SearchQueryBuilder searchQueryBuilder = new SearchQueryBuilder();

        List<RadSearch> radSearches = searchRadRepository.search(searchQueryBuilder.createMoreLikeThisQuery(moreLikeThisDTO)).getContent();

        System.out.println(radSearches.size());
        return radSearches;
    }
    public List<RecenzentSearch> searchGeo(GeoPoint geoPoint){
        SearchQueryBuilder searchQueryBuilder = new SearchQueryBuilder();

        List<RecenzentSearch> recenzentSearches = searchRecenzentRepository.search(searchQueryBuilder.createGeoQuery(geoPoint)).getContent();

        System.out.println(recenzentSearches.size());
        return recenzentSearches;
    }


}
