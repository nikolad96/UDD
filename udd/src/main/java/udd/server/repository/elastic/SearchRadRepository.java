package udd.server.repository.elastic;


import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import udd.server.model.Rad;
import udd.server.model.RadSearch;


public interface SearchRadRepository extends ElasticsearchRepository<RadSearch, String> {

    RadSearch findOneById(String Id);
}
