package com.portfolio.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.dto.PortfolioDto;

@RestController
@RequestMapping("/api/client")
@CrossOrigin(origins = "*")
@PreAuthorize("hasRole('CLIENT')")
public class ClientController {

    @GetMapping("/portfolios/purchased")
    public List<PortfolioDto> getPurchased() {
        return IntStream.rangeClosed(1, 3)
            .mapToObj(i -> new PortfolioDto(
                (long) i,
                "Purchased " + i,
                "author" + i,
                "APPROVED",
                LocalDateTime.now().minusDays(i * 2).toString()
            ))
            .collect(Collectors.toList());
    }

    @GetMapping("/portfolios/recommended")
    public List<PortfolioDto> getRecommended() {
        return IntStream.rangeClosed(1, 4)
            .mapToObj(i -> new PortfolioDto(
                100L + i,
                "Recommended " + i,
                "expert" + i,
                "APPROVED",
                LocalDateTime.now().minusDays(i).toString()
            ))
            .collect(Collectors.toList());
    }
}
