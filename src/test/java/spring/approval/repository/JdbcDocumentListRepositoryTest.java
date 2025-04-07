package spring.approval.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import spring.approval.domain.DocumentList;
import spring.approval.service.DocumentListService;

@SpringBootTest
@Transactional
class JdbcDocumentListRepositoryTest {

    @Autowired DocumentListService documentListService;
    @Autowired
    IDocumentListRepository IDocumentListRepository;

//    @Test
//    void 리스트조회_검색어_O() {
//        //given
//        //when
//        List<DocumentList> resultList = IDocumentListRepository.findList(1L, "AND title LIKE '%지출%'", 1);
//
//        //then
//        assertThat(resultList.size()).isEqualTo(4);
//    }
//
//    @Test
//    void 리스트조회_검색어_X() {
//        //given
//        //when
//        List<DocumentList> resultList = IDocumentListRepository.findList(1L, "", 1);
//
//        //then
//        assertThat(resultList.size()).isEqualTo(10);
//    }
}