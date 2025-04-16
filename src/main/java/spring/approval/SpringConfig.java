package spring.approval;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.approval.repository.DocumentListRepository;
import spring.approval.repository.IDocumentListRepository;
import spring.approval.repository.user.JdbcTemplateMemberRepository;
import spring.approval.repository.user.MemberRepository;
import spring.approval.service.DocumentListService;
import spring.approval.service.MemberService;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new JdbcTemplateMemberRepository(dataSource);
    }

}
