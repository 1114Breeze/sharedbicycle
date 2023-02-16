import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;

/**
 * @author test
 * @classname SharedBikeCompany
 * @date 2023/2/9 9:34
 */
@Data
    public class SharedBikeCompany {
    private int bikeCompanyId;// 公司id
    private String bikeCompanyName;// ofo 哈罗 摩拜
    private ArrayList<SharedBike> sharedBikes = new ArrayList<SharedBike>();// 公司持有共享单车
    private int sum;//公司单车总量
    private int count;// 公司单车借出次数

}
