package commerce.app.repository.search;

import commerce.app.domain.Produit;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link Produit} entity.
 */
public interface ProduitSearchRepository extends ElasticsearchRepository<Produit, Long> {
}
