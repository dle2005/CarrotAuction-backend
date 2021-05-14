package carrotauction.com.carrotauction.model.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String location;

    private String nickname;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<ItemBider> itemBiderList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<FavoriteItem> favoriteItemList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Alarm> alarmList;

    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy
    private String updatedBy;

}
