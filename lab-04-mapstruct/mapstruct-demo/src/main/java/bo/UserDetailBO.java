package bo;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserDetailBO {
    private Integer userId;

    public UserDetailBO setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }
}
