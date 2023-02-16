import lombok.Data;

/**
 * @author test
 * @classname SharedBike
 * @date 2023/2/9 9:34
 */

@Data
public class SharedBike {
    private int bid;// 单车编号
    private String bname;// 单车名称-->ofo 哈罗 摩拜
    private int status;// 单车状态--> 0 已借出 1 可借状态
    private String borrowTime;// 单车借出时间

}
