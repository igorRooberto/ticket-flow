package com.igor.Ticket_flow.services;

import com.igor.Ticket_flow.domain.entities.Customer;
import com.igor.Ticket_flow.exceptions.NotFoundException;
import com.igor.Ticket_flow.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer findById(Long id){
        return customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente Não Encontrado"));
    }



}
