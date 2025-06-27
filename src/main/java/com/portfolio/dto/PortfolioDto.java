package com.portfolio.dto;

public class PortfolioDto {
    private Long id;
    private String title;
    private String author;
    private String status;    // e.g. "PENDING" or "APPROVED"
    private String createdAt; // ISO date string

    public PortfolioDto() {}

    public PortfolioDto(Long id, String title, String author, String status, String createdAt) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.status = status;
        this.createdAt = createdAt;
    }

    // getters & setters
    public Long getId()            { return id; }
    public void setId(Long id)     { this.id = id; }
    public String getTitle()       { return title; }
    public void setTitle(String t) { this.title = t; }
    public String getAuthor()      { return author; }
    public void setAuthor(String a){ this.author = a; }
    public String getStatus()      { return status; }
    public void setStatus(String s){ this.status = s; }
    public String getCreatedAt()       { return createdAt; }
    public void setCreatedAt(String c)  { this.createdAt = c; }
}
