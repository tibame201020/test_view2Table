package com.mademo.view2table.repo;

import com.mademo.view2table.model.Account;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface AccountRepo extends ReactiveCrudRepository<Account, Long> {
}
