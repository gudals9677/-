package kr.co.springboard.service;

import kr.co.springboard.dto.TermsDTO;
import kr.co.springboard.entity.Terms;
import kr.co.springboard.repository.TermsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class TermsService {

    private final TermsRepository repository;
    private final ModelMapper modelMapper;

    public TermsDTO selectTerms(){

        Optional<Terms> result = repository.findById(1);

        Terms terms = result.get();

        return modelMapper.map(terms,TermsDTO.class);

    }
}
