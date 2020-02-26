package udd.server.repository.elastic;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import udd.server.model.RadSearch;
import udd.server.model.RecenzentSearch;

public interface SearchRecenzentRepository extends ElasticsearchRepository<RecenzentSearch, String> {
    RecenzentSearch findOneById(String Id);
}
