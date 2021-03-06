package com.codexsoft.model;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Indexed
public class Subproblem extends BaseEntity {

    @Id
    @DocumentId
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO,
            analyzer = @Analyzer(impl = StandardAnalyzer.class))
    private String title;

    @Column(length=7000, nullable = false)
    @Field(index=Index.YES, analyze= Analyze.YES, store=Store.NO,
            analyzer = @Analyzer(impl = StandardAnalyzer.class))
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    Task task;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
