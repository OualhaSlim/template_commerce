package commerce.app.service.impl;

import commerce.app.service.ProduitService;
import commerce.app.domain.Produit;
import commerce.app.repository.ProduitRepository;
import commerce.app.repository.search.ProduitSearchRepository;
import commerce.app.service.dto.ProduitDTO;
import commerce.app.service.mapper.ProduitMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link Produit}.
 */
@Service
@Transactional
public class ProduitServiceImpl implements ProduitService {

    private final Logger log = LoggerFactory.getLogger(ProduitServiceImpl.class);

    private final ProduitRepository produitRepository;

    private final ProduitMapper produitMapper;

    private final ProduitSearchRepository produitSearchRepository;

    public ProduitServiceImpl(ProduitRepository produitRepository, ProduitMapper produitMapper, ProduitSearchRepository produitSearchRepository) {
        this.produitRepository = produitRepository;
        this.produitMapper = produitMapper;
        this.produitSearchRepository = produitSearchRepository;
    }

    @Override
    public ProduitDTO save(ProduitDTO produitDTO) {
        log.debug("Request to save Produit : {}", produitDTO);
        Produit produit = produitMapper.toEntity(produitDTO);
        produit = produitRepository.save(produit);
        ProduitDTO result = produitMapper.toDto(produit);
        produitSearchRepository.save(produit);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProduitDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Produits");
        return produitRepository.findAll(pageable)
            .map(produitMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ProduitDTO> findOne(Long id) {
        log.debug("Request to get Produit : {}", id);
        return produitRepository.findById(id)
            .map(produitMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Produit : {}", id);
        produitRepository.deleteById(id);
        produitSearchRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProduitDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Produits for query {}", query);
        return produitSearchRepository.search(queryStringQuery(query), pageable)
            .map(produitMapper::toDto);
    }
}
