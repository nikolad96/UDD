package udd.server.search;

import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.index.query.*;

import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.MoreLikeThisQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import udd.server.dto.MoreLikeThisDTO;
import udd.server.dto.MultiFieldQueryDTO;
import udd.server.dto.QueryDTO;
import udd.server.model.Rad;

import java.util.ArrayList;
import java.util.List;

public class SearchQueryBuilder {



    public NativeSearchQuery createQuery(QueryDTO queryDTO){
        QueryBuilder qb = null;
        System.out.println(queryDTO.getVrednost().startsWith("\""));
        System.out.println(queryDTO.getVrednost().endsWith("\""));
        System.out.println(queryDTO.getVrednost());
        System.out.println(queryDTO.getPolje());
        if((queryDTO.getVrednost().startsWith("\'")||queryDTO.getVrednost().startsWith("\""))&&(queryDTO.getVrednost().endsWith("\'")||queryDTO.getVrednost().endsWith("\"")))
        {
            qb = QueryBuilders.matchPhraseQuery(queryDTO.getPolje(), queryDTO.getVrednost());
        }
        else
        {
            qb = QueryBuilders.matchQuery(queryDTO.getPolje(), queryDTO.getVrednost());
        }
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder().withQuery(qb).withHighlightFields(new HighlightBuilder.Field(queryDTO.getPolje())).build();
        return nativeSearchQuery;
    }

    public NativeSearchQuery createMultiFieldQuery(MultiFieldQueryDTO multiFieldQueryDTO){
        BoolQueryBuilder qb = QueryBuilders.boolQuery();
        List<QueryBuilder> queryBuilders = createBuilders(multiFieldQueryDTO);
        if(multiFieldQueryDTO.getTip().equals("and"))
        {
        for(QueryBuilder q : queryBuilders){
            qb.must(q);
        }
        }
        else{
            for(QueryBuilder q : queryBuilders){
                qb.should(q);
            }
        }
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder().withQuery(qb).build();
        return nativeSearchQuery;
    }
    public SearchQuery createMoreLikeThisQuery(MoreLikeThisDTO moreLikeThisDTO){
        MoreLikeThisQueryBuilder moreLikeThisQueryBuilder = QueryBuilders.moreLikeThisQuery(new String[]{"text"}, new String[]{moreLikeThisDTO.getVrednost()},null).minTermFreq(moreLikeThisDTO.getMin()).maxQueryTerms(moreLikeThisDTO.getMax()).minDocFreq(1);
//        MoreLikeThisQueryBuilder moreLikeThisQueryBuilder = QueryBuilders.moreLikeThisQuery(new String[]{"oblast"}, new String[]{"Geografija"},null).minTermFreq(moreLikeThisDTO.getMin()).maxQueryTerms(moreLikeThisDTO.getMax()).minDocFreq(1);
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(moreLikeThisQueryBuilder).build();
        return searchQuery;
    }

    public SearchQuery createGeoQuery(GeoPoint geoPoint){
        GeoDistanceQueryBuilder geoDistanceQueryBuilder = QueryBuilders.geoDistanceQuery("location").point(geoPoint).distance("100km").geoDistance(GeoDistance.ARC);
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(geoDistanceQueryBuilder).build();
        return searchQuery;
    }



    public List<QueryBuilder> createBuilders(MultiFieldQueryDTO multiFieldQueryDTO){
        List<QueryBuilder> queryBuilders = new ArrayList<>();
            for(QueryDTO q : multiFieldQueryDTO.getQueryDTOList()){
                if(q.getVrednost()!=null && q.getVrednost()!="")
                    if((q.getVrednost().startsWith("\'")||q.getVrednost().startsWith("\""))&&(q.getVrednost().endsWith("\'")||q.getVrednost().endsWith("\"")))
                    {
                        queryBuilders.add(QueryBuilders.matchPhraseQuery(q.getPolje(), q.getVrednost()));
                    }
                else{
                        queryBuilders.add(QueryBuilders.matchQuery(q.getPolje(), q.getVrednost()));
                    }

            }
            return queryBuilders;

    }
    public SearchQueryBuilder(){

    }

}
