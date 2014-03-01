package com.codexsoft.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition="LONGBLOB")
    private byte[] logo;

    @Column(length = 1000, nullable = false)
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
    @JoinTable(name = "task_user",
            joinColumns = {@JoinColumn(name = "task_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<User> users = new HashSet<User>();


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

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
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
}
