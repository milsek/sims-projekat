package com.example.library.service;

import com.example.library.model.Edition;
import com.example.library.model.dto.EditionDisplayDto;
import com.example.library.repository.EditionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EditionService {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EditionRepository editionRepository;

    @Autowired
    private BookTitleService titleService;

    public List<EditionDisplayDto> getTopTen() {
        List<Edition> editions = editionRepository.findTop10ByOrderByRatingDesc();
        return editions.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public List<EditionDisplayDto> getTopReads() {
        List<Edition> editions = editionRepository.findTop16ByOrderByReadsDesc();
        return editions.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public List<Edition> getAll() {
        return editionRepository.findAll();
    }

    public Edition getEditionById(long id) {
        Optional<Edition> result = editionRepository.findById(id);
        return result.orElse(null);
    }

    public Edition getEditionByBookId(long id) {
        return editionRepository.findEditionByCopies_Id(id);
    }

    public Map<Long, List<Edition>> searchEditions(String text, int page, int amount) {
        return editionRepository.searchEditions(text, page, amount);
    }

    public Set<EditionDisplayDto> getRelatedEditions(Long id) {
        Edition edition = getEditionById(id);
        if (edition == null)
            return null;

        Set<Edition> editions = titleService.getAllEditionsByTitleId(edition.getTitle().getId());
        return editions.stream().map(this::entityToDto).collect(Collectors.toSet());
    }

    private EditionDisplayDto entityToDto(Edition edition) {
        return modelMapper.map(edition, EditionDisplayDto.class);
    }
}
