package com.mademo.view2table.contorller;

import com.mademo.view2table.model.Account;
import com.mademo.view2table.model.Account_TESTView;
import com.mademo.view2table.repo.AccountRepo;
import com.mademo.view2table.repo.Account_TESTViewRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestApi {
    private final Account_TESTViewRepo account_testViewRepo;
    private final AccountRepo accountRepo;

    public RestApi(Account_TESTViewRepo account_testViewRepo, AccountRepo accountRepo) {
        this.account_testViewRepo = account_testViewRepo;
        this.accountRepo = accountRepo;
    }

    @RequestMapping("/getAllViews")
    public Flux<Account_TESTView> getViews(){
        return account_testViewRepo.findAll();
    }

    @RequestMapping("/transFromView")
    public Flux<Account> transFromView(@RequestBody List<Account_TESTView> account_testViewList) {
        return Flux.fromIterable(account_testViewList)
                .map(account_testView -> {
                    Account account = new Account();
                    BeanUtils.copyProperties(account_testView, account);
                    return account;
                });
    }
}
