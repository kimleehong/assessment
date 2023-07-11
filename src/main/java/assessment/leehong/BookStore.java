package assessment.leehong;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class BookStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    private String name;

    @OneToMany(mappedBy = "bookStore", cascade = CascadeType.REMOVE)
    private List<Book> holdingBooks;
}
