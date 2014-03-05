package com.codexsoft.model;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Indexed
public class Task {

    @Id
    @DocumentId
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO,
            analyzer = @Analyzer(impl = StandardAnalyzer.class))
    private String title;

    @Column(columnDefinition="LONGBLOB")
    private byte[] logo;

    @Column(length = 1000, nullable = false)
    @Field(index=Index.YES, analyze= Analyze.YES, store=Store.NO,
            analyzer = @Analyzer(impl = StandardAnalyzer.class))
    private String description;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<Subproblem> subproblems = new ArrayList<Subproblem>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<Comment> comments = new ArrayList<Comment>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    User author;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "task_subscriber",
            joinColumns = {@JoinColumn(name = "task_id")},
            inverseJoinColumns = {@JoinColumn(name = "subscriber_id")})
    private Set<User> subscribers = new HashSet<User>();


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Subproblem> getSubproblems() {
        return subproblems;
    }

    public void setSubproblems(Collection<Subproblem> subproblems) {
        this.subproblems = subproblems;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Collection<Comment> getComments() {
        return comments;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public Set<User> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(Set<User> subscribers) {
        this.subscribers = subscribers;
    }
}
