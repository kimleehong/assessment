package assessment.leehong;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class LeehongApplicationTests {
    @Autowired
    private BookStoreRepository bookStoreRepository;
    @Autowired
    private BookRepository bookRepository;

// 서점 두개를 만들어주세요.

// 서점은 각각 이름과 보유서적으로 구성됩니다.

// 각각 코리아서점, 아이티문고

    @Test
    void t2() {
        BookStore bookStore1 = new BookStore();
        bookStore1.setName("코리아서점");

        BookStore bookStore2 = new BookStore();
        bookStore2.setName("아이티문고");

        this.bookStoreRepository.save(bookStore1);
        this.bookStoreRepository.save(bookStore2);

        System.out.println(bookStore1.getName());
        System.out.println(bookStore2.getName());
    }


// 모든 서점 정보 조회

// 출력 : 코리아서점, 아이티문고

    @Test
    void t3() {
        List<BookStore> bookStore = this.bookStoreRepository.findAll();

        for (BookStore b : bookStore) {
            System.out.println(b.getName());
        }

    }


// 아이티문고의 이름을 IT문고로 변경해주세요.

// 변경 후엔 t3() 메서드를 실행해 확인해주세요.

// 출력 : 코리아서점, IT문고

    @Test
    void t4() {
        Optional<BookStore> ob = this.bookStoreRepository.findById(2);
        if (ob.isPresent()) {
            BookStore bookStore = ob.get();
            bookStore.setName("IT문고");
            this.bookStoreRepository.save(bookStore);
        }
        System.out.println(ob.get().getName());
    }


// 책 5권을 만들어주세요.

// 책은 제목과 저자, 보유 서점으로 구성됩니다.

// 책은 각각

// 어린왕자, 생떽쥐페리, 코리아서점

// 해리포터, 조앤 롤링, 코리아서점

// 죄와벌, 도스토예프스키, 코리아서점

// 점프 투 스프링부트, 박응용, 아이티문고

// 수학의 정석, 홍성대, 아이티문고

// 로 만들어주세요.


    @Test
    void t5() {
        Optional<BookStore> ob1 = this.bookStoreRepository.findById(1);
        assertTrue(ob1.isPresent());
        BookStore bookStore1 = ob1.get();

        Optional<BookStore> ob2 = this.bookStoreRepository.findById(2);
        assertTrue(ob2.isPresent());
        BookStore bookStore2 = ob2.get();

        Book book1 = new Book();
        book1.setTitle("어린왕자");
        book1.setAuthor("생떽쥐페리");
        book1.setBookStore(bookStore1);
        bookRepository.save(book1);

        // 해리포터
        Book book2 = new Book();
        book2.setTitle("해리포터");
        book2.setAuthor("조앤 롤링");
        book2.setBookStore(bookStore1);
        bookRepository.save(book2);

        // 죄와벌
        Book book3 = new Book();
        book3.setTitle("죄와벌");
        book3.setAuthor("도스토예프스키");
        book3.setBookStore(bookStore1);
        bookRepository.save(book3);

        // 점프 투 스프링부트
        Book book4 = new Book();
        book4.setTitle("점프 투 스프링부트");
        book4.setAuthor("박응용");
        book4.setBookStore(bookStore2);
        bookRepository.save(book4);

        // 수학의 정석
        Book book5 = new Book();
        book5.setTitle("수학의 정석");
        book5.setAuthor("홍성대");
        book5.setBookStore(bookStore2);
        bookRepository.save(book5);
    }

// 모든 책의 제목을 출력해주세요.

    @Test
    void t6() {

// 구현 및 테스트
        List<Book> books = bookRepository.findAll();

        System.out.println("모든 책의 제목:");
        for (Book book : books) {
            System.out.println(book.getTitle());
        }

    }


// 코리아서점에서 판매하는 책의 제목을 출력해주세요.

    @Transactional
    @Test
    void t7() {

// 구현 및 테스트
        Optional<BookStore> ob = this.bookStoreRepository.findById(1);
        assertTrue(ob.isPresent());
        BookStore bookStore = ob.get();

        List<Book> books = bookStore.getHoldingBooks();

        System.out.println("코리아서점에서 판매하는 책의 제목:");
        for (Book book : books) {
            System.out.println(book.getTitle());
        }


    }


// 박응용이 쓴 책을 삭제해주세요.

    // 삭제 후 t6 메서드를 이용해 확인해주세요.
    @Transactional
    @Test
    void t8() {

// 구현 및 테스트
        Book b = this.bookRepository.findByAuthor("박응용");
        this.bookRepository.delete(b);
    }
}