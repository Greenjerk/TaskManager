package com.codexsoft.service;

import com.codexsoft.model.Task;

import java.util.Set;

/**
 * User: Greenjerk
 * Date: 30.01.14
 * Time: 15:28
 */

public interface SearchService {
    public Set<Task> search(String term);
}
