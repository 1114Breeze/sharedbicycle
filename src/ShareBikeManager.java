import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author test
 * @classname ShareBikeManager
 * @date 2023/2/9 9:36
 */
public class ShareBikeManager {
    //格式化时间
    static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //初始化单车公司编号
    static int bikeCompanyId;
    //初始化单车公司
    static ArrayList<SharedBikeCompany> sharedBikeCompanies = new ArrayList<SharedBikeCompany>();
    //初始化日志数据
    static ArrayList<String> log = new ArrayList<String>();

    public static void main(String[] args) throws ParseException {
        System.out.println("欢迎使用迷你共享单车管理系统");
        System.out.println("*************************************************");
        initial(sharedBikeCompanies);
        while (true) {
            System.out.println("*******************主菜单*************************");
            System.out.println("                1.单车管理");
            System.out.println("                2.单车公司管理");
            System.out.println("                3.排行榜");
            System.out.println("                4.操作日志");
            System.out.println("                5.退出系统");
            System.out.println("*************************************************");
            Scanner scanner = new Scanner(System.in);
            System.out.println("请选择：");
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    sharedBike();
                    break;
                case "2":
                    sharedBikeCompany();
                    break;
                case "3":
                    rank(sharedBikeCompanies);
                    break;
                case "4":
                    findLog(log);
                    break;
                case "5":
                    System.out.println("谢谢使用");
                    System.exit(1);
            }
        }
    }

    //初始化的方法
    public static void initial(ArrayList<SharedBikeCompany> sharedBikeCompanies) {
        Scanner scanner = new Scanner(System.in);

        SharedBikeCompany sharedBikeCompany1 = new SharedBikeCompany();
        SharedBikeCompany sharedBikeCompany2 = new SharedBikeCompany();
        SharedBikeCompany sharedBikeCompany3 = new SharedBikeCompany();

        sharedBikeCompany1.setBikeCompanyId(1);
        sharedBikeCompany1.setBikeCompanyName("ofo单车");

        sharedBikeCompany2.setBikeCompanyId(2);
        sharedBikeCompany2.setBikeCompanyName("halo单车");

        sharedBikeCompany3.setBikeCompanyId(3);
        sharedBikeCompany3.setBikeCompanyName("青桔单车");

        sharedBikeCompanies.add(sharedBikeCompany1);
        sharedBikeCompanies.add(sharedBikeCompany2);
        sharedBikeCompanies.add(sharedBikeCompany3);

        int id = 0;
        //列出模板数据中单车公司信息
        for (SharedBikeCompany sharedBikeCompany : sharedBikeCompanies) {
            System.out.println(++id + "." + sharedBikeCompany.getBikeCompanyName());
        }
        System.out.println("是否要使用默认的数据,将以上单车公司作为模板（y/n）");
        String input = scanner.nextLine();
        if ("y".equals(input)) {
            //将单车公司编号从3开始计数
            bikeCompanyId = sharedBikeCompanies.size();
            System.out.println("添加模板信息成功");
            return;
        }
        //将单车公司编号从0开始计数
        bikeCompanyId = 0;
        //删除模板数据
        sharedBikeCompanies.clear();
        //调用添加单车公司的方法
        addCompany(sharedBikeCompanies);

    }

    //单车管理菜单管理
    private static void sharedBike() throws ParseException {
        while (true) {
            System.out.println("*****************单车管理*******************");
            System.out.println("                0.主菜单");
            System.out.println("                1.投放Bike");
            System.out.println("                2.查看Bike");
            System.out.println("                3.删除Bike");
            System.out.println("                4.借出Bike");
            System.out.println("                5.归还Bike");
            System.out.println("                6.退出系统");
            System.out.println("********************************************");
            Scanner scanner = new Scanner(System.in);
            System.out.println("请选择：");
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    putBike(sharedBikeCompanies);
                    break;
                case "2":
                    queryBike(sharedBikeCompanies);
                    break;
                case "3":
                    deleteBike(sharedBikeCompanies);
                    break;
                case "4":
                    lend(sharedBikeCompanies);
                    break;
                case "5":
                    returnBike(sharedBikeCompanies);
                    break;
                case "6":
                    System.out.println("谢谢使用");
                    System.exit(1);
                case "0":
                    return;
            }
        }
    }

    //单车公司管理菜单管理
    private static void sharedBikeCompany() {
        while (true) {
            System.out.println("*****************单车公司管理*****************");
            System.out.println("                0.主菜单");
            System.out.println("                1.添加单车公司");
            System.out.println("                2.查看单车公司");
            System.out.println("                3.删除单车公司");
            System.out.println("                4.退出系统");
            System.out.println("********************************************");
            Scanner scanner = new Scanner(System.in);
            System.out.println("请选择：");
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    addCompany(sharedBikeCompanies);
                    break;
                case "2":
                    findCompany(sharedBikeCompanies);
                    break;
                case "3":
                    deleteCompany(sharedBikeCompanies);
                    break;
                case "4":
                    System.out.println("谢谢使用");
                    System.exit(1);
                case "0":
                    return;
            }
        }
    }

    //投放单车的方法
    private static void putBike(ArrayList<SharedBikeCompany> sharedBikeCompanies) {
        System.out.println("--------->单车管理---->投放Bike");
        System.out.println("");
        //遍历输出要选择的单车公司名称
        int id = 0;
        for (SharedBikeCompany sharedBikeCompany : sharedBikeCompanies) {
            System.out.println(++id + "." + sharedBikeCompany.getBikeCompanyName());
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("请选择要投放的单车公司(按0返回上一页)");
        int input = scanner.nextInt();
        if (input == 0) {
            return;
        }
        //输入的编号大于公司数量时返回
        if (input > sharedBikeCompanies.size()) {
            System.out.println("没有此单车公司的编号");
            return;
        }
        //获取要投放单车的公司
        SharedBikeCompany sharedBikeCompany = sharedBikeCompanies.get(input - 1);
        //获取对应单车公司下的单车
        ArrayList<SharedBike> sharedBikes = sharedBikeCompany.getSharedBikes();
        //获取要选择投放单车的公司名称
        String companyName = sharedBikeCompany.getBikeCompanyName();
//        if (input == 1) {
//            bikeName = "ofo单车";
//        }
//        if (input == 2) {
//            bikeName = "halo单车";
//        }
//        if (input == 3) {
//            bikeName = "摩拜单车";
//        }
        System.out.println("请选择要投放的数量(按0返回上一页)");
        int sum = scanner.nextInt();
        if (sum == 0) {
            //重新进入该方法
            putBike(sharedBikeCompanies);
            return;
        }
        //初始化对应公司下单车的编号
        int bikeCount;
        //对应公司下单车的数量
        int num = sharedBikes.size();
        //还未被投放单车时
        if (num == 0) {
            //单车编号设置为0
            bikeCount = 0;
        } else {
            //单车编号设置为当前公司下最后一个单车的编号
            bikeCount = (sharedBikes.get(sharedBikes.size() - 1).getBid());
        }
        //遍历投放录入数量的单车
        for (int i = 0; i < sum; i++) {
            SharedBike sharedBike = new SharedBike();
            sharedBike.setBid(++bikeCount);
            sharedBike.setBname(companyName + "" + bikeCount);
            //投放后的单车默认为可借状态
            sharedBike.setStatus(1);
            sharedBike.setBorrowTime("");
            //将单车添加到集合中
            sharedBikes.add(sharedBike);
        }
        //将公司的单车设置为刚刚单车的集合
        sharedBikeCompany.setSharedBikes(sharedBikes);
        //将公司单车数量设置为之前的数量+投放的数量
        sharedBikeCompany.setSum((sharedBikeCompany.getSum()) + sum);
        //获取系统当前的时间
        String now = df.format(System.currentTimeMillis());
        //将信息存入日志中
        log.add("投放" + sum + "辆" + companyName + "  " + now);
        System.out.println("投放" + sum + "辆" + companyName + "成功");
    }

    //查看单车的方法
    private static void queryBike(ArrayList<SharedBikeCompany> sharedBikeCompanies) {
        System.out.println("--------->单车管理---->查看Bike");
        //遍历输出各个单车公司的内容
        for (SharedBikeCompany sharedBikeCompany : sharedBikeCompanies) {
            System.out.println("+---------------+---------------+---------------+------------+");
            System.out.println("|     公司序号   |" + "   公司名称   |" + "公司单车数量" + "|公司借出单车次数|");
            System.out.println("+---------------+---------------+---------------+------------+");
            System.out.println("       " + sharedBikeCompany.getBikeCompanyId() + "     |     "
                    + sharedBikeCompany.getBikeCompanyName() + "     |     "
                    + sharedBikeCompany.getSum() + "     |     " + sharedBikeCompany.getCount());
            System.out.println("");
            System.out.println("单车序号 " + "  单车名称  " + "   单车状态  " + "借出时间");
            //遍历输出对应单车公司下单车的内容
            pagination(sharedBikeCompany.getSharedBikes());
//            for (SharedBike sharedBike : sharedBikeCompany.getSharedBikes()) {
//                System.out.println(sharedBike.getBid() + "         " + sharedBike.getBname() + "     " + (sharedBike.getStatus() == 1 ? "可借" : "已借出") + "  " + sharedBike.getBorrowTime());
//            }
            System.out.println("");
            System.out.println("");
        }
    }

    //删除单车的方法
    private static void deleteBike(ArrayList<SharedBikeCompany> sharedBikeCompanies) {
        System.out.println("--------->单车管理---->删除Bike");
        System.out.println("");
        int id = 0;
        //遍历输出要选择的单车公司名称
        for (SharedBikeCompany sharedBikeCompany : sharedBikeCompanies) {
            System.out.println(++id + "." + sharedBikeCompany.getBikeCompanyName());
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("请选择要删除的单车公司(按0返回)");
        int input = scanner.nextInt();
        if (input == 0) {
            return;
        }
        //输入的编号大于公司数量时返回
        if (input > sharedBikeCompanies.size()) {
            System.out.println("没有此单车公司的编号");
            return;
        }
        //获取要删除单车的公司
        SharedBikeCompany sharedBikeCompany = sharedBikeCompanies.get(input - 1);
        //获取对应单车公司下的单车
        ArrayList<SharedBike> sharedBikes = sharedBikeCompany.getSharedBikes();

        System.out.println(sharedBikeCompany.getBikeCompanyName() + "公司的共享单车有如下");
        System.out.println("单车序号 " + "  单车名称  " + "   单车状态  " + "借出时间");
        //遍历输出对应公司下的单车
        for (SharedBike sharedBike : sharedBikes) {
            System.out.println(sharedBike.getBid() + "         " + sharedBike.getBname() +
                    "     " + (sharedBike.getStatus() == 1 ? "可借" : "已借出") + "  " + sharedBike.getBorrowTime());
        }
        System.out.println("");
        System.out.println("请选择要删除的单车编号(按0返回上一页)");
        int bikeBid = scanner.nextInt();
        if (bikeBid == 0) {
            //重新进入该方法
            deleteBike(sharedBikeCompanies);
            return;
        }
        //判断单车公司是否存在对应编号的单车，并且没有被借出
        Boolean flag = false;
        for (int i = 0; i < sharedBikes.size(); i++) {
            SharedBike sharedBike = sharedBikes.get(i);
            if (sharedBike.getBid() == bikeBid && sharedBike.getStatus() == 1) {
                System.out.println("删除" + sharedBike.getBname() + "成功");
                //获取当前时间
                String now = df.format(System.currentTimeMillis());
                //将信息存入日志中
                log.add("删除" + sharedBike.getBname() + "  " + now);
                sharedBikes.remove(i);
                //删除后对应单车公司单车总量-1
                sharedBikeCompany.setSum((sharedBikeCompany.getSum()) - 1);
                flag = true;
            }
        }
        //循环完没有完成删除单车的操作时打印的内容
        if (!flag) {
            System.out.println("没有此单车或此单车已被借出");
        }
    }

    //借出单车的方法
    private static void lend(ArrayList<SharedBikeCompany> sharedBikeCompanies) {
        System.out.println("--------->单车管理---->借出Bike");
        System.out.println("");
        int id = 0;
        //遍历输出要选择的单车公司名称
        for (SharedBikeCompany sharedBikeCompany : sharedBikeCompanies) {
            System.out.println(++id + "." + sharedBikeCompany.getBikeCompanyName());
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("请选择要借车的单车公司（按0返回上一页）");
        int input = scanner.nextInt();
        if (input == 0) {
            return;
        }
        //输入的编号大于公司数量时返回
        if (input > sharedBikeCompanies.size()) {
            System.out.println("没有此单车公司的编号");
            return;
        }
        //获取要借出单车的公司
        SharedBikeCompany sharedBikeCompany = sharedBikeCompanies.get(input - 1);
        //获取对应单车公司下的单车
        ArrayList<SharedBike> sharedBikes = sharedBikeCompany.getSharedBikes();
        //显示出未被借出的单车
        System.out.println(sharedBikeCompany.getBikeCompanyName() + "公司下可以被借出的共享单车有");
        System.out.println("单车序号 " + "  单车名称  " + "   单车状态  " + "借出时间");
        for (SharedBike sharedBike : sharedBikes) {
            if (sharedBike.getStatus() == 1) {
                System.out.println(sharedBike.getBid() + "         " + sharedBike.getBname() +
                        "     " + (sharedBike.getStatus() == 1 ? "可借" : "已借出") + "  " + sharedBike.getBorrowTime());
            }
        }
        System.out.println("");
        System.out.println("请选择要借车的单车编号(按0返回上一页)");
        int bikeBid = scanner.nextInt();
        if (bikeBid == 0) {
            //重新进入该方法
            lend(sharedBikeCompanies);
            return;
        }
        //判断单车公司是否存在对应编号的单车并且未被借出
        Boolean flag = false;

        for (int i = 0; i < sharedBikes.size(); i++) {
            SharedBike sharedBike = sharedBikes.get(i);
            //判断条件
            if (sharedBike.getBid() == bikeBid && sharedBike.getStatus() == 1) {
                //获取系统当前时间
                String now = df.format(System.currentTimeMillis());
                //修改对应数据
                sharedBike.setBorrowTime(now);
                sharedBike.setStatus(0);
                //将索引i处的对象修改为更改过后的
                sharedBikes.set(i, sharedBike);
                System.out.println("借出" + sharedBikeCompany.getBikeCompanyName() + "公司下的<<" + sharedBike.getBname() + ">>成功");
                //将信息存入日志
                log.add("借出" + sharedBikeCompany.getBikeCompanyName() + "公司下的<<" + sharedBike.getBname() + ">>  " + now);
                //获取当前单车公司的借出单车数量
                int count = sharedBikeCompany.getCount();
                //借出成功后对应单车公司的借出单车数量累增1
                sharedBikeCompany.setCount(++count);
                flag = true;
            }
        }
        //循环完没有完成借出单车的操作时打印的内容
        if (!flag) {
            System.out.println(sharedBikeCompany.getBikeCompanyName() + "公司下没有此单车或已被借出");
        }
    }

    //归还单车的方法
    private static void returnBike(ArrayList<SharedBikeCompany> sharedBikeCompanies) throws ParseException {
        System.out.println("--------->单车管理---->归还Bike");
        System.out.println("");
        int id = 0;
        //遍历输出要选择的单车公司名称
        for (SharedBikeCompany sharedBikeCompany : sharedBikeCompanies) {
            System.out.println(++id + "." + sharedBikeCompany.getBikeCompanyName());
        }
//        System.out.println("1.ofo单车");
//        System.out.println("2.halo单车");
//        System.out.println("3.摩拜单车");
        Scanner scanner = new Scanner(System.in);
        System.out.println("请选择要归还的单车编号(按0返回上一页)");
        int input = scanner.nextInt();
        if (input == 0) {
            return;
        }
        //输入的编号大于公司数量时返回
        if (input > sharedBikeCompanies.size()) {
            System.out.println("没有此单车公司的编号");
            return;
        }
        //获取要投放单车的公司
        SharedBikeCompany sharedBikeCompany = sharedBikeCompanies.get(input - 1);
        //获取对应单车公司下的单车
        ArrayList<SharedBike> sharedBikes = sharedBikeCompany.getSharedBikes();
        //显示出要归还的单车
        System.out.println("你需要归还" + sharedBikeCompany.getBikeCompanyName() + "公司的单车有");
        System.out.println("单车序号 " + "  单车名称  " + "   单车状态  " + "借出时间");
        for (SharedBike sharedBike : sharedBikes) {
            if (sharedBike.getStatus() == 0) {
                System.out.println(sharedBike.getBid() + "         " + sharedBike.getBname() + "     "
                        + (sharedBike.getStatus() == 1 ? "可借" : "已借出") + "  " + sharedBike.getBorrowTime());
            }
        }
        System.out.println("");
        System.out.println("请输入要归还的单车编号(按0返回上一页)");
        int bikeBid = scanner.nextInt();
        if (bikeBid == 0) {
            returnBike(sharedBikeCompanies);
            return;
        }
        //判断单车公司是否存在对应编号的单车并且被借出
        Boolean flag = false;
        for (int i = 0; i < sharedBikes.size(); i++) {
            SharedBike sharedBike = sharedBikes.get(i);
            //判断条件
            if (sharedBike.getBid() == bikeBid && sharedBike.getStatus() == 0) {
                //获取系统当前时间
                String now = df.format(System.currentTimeMillis());
                //获取系统当前时间戳
                long now1 = System.currentTimeMillis();
                //获取借出时的时间数据
                Date before1 = df.parse(sharedBike.getBorrowTime());
                //将其都转化为时间戳之后相减/1000获得到秒数
                double charge = (double) ((now1 - before1.getTime()) / 1000);
                //将单车设置为可借的状态
                sharedBike.setStatus(1);
                //修改对应索引处的单车
                sharedBikes.set(i, sharedBike);
                System.out.println("归还" + sharedBikeCompany.getBikeCompanyName() + "公司下的<<" + sharedBike.getBname() + ">>成功");
                System.out.println("你的借车时间为：" + sharedBike.getBorrowTime());
                System.out.println("你的归还时间为：" + now);
                System.out.println("用车时间为" + charge + "秒，需支付：" + (charge / 10) + "元");
                //将信息存入日志
                log.add("归还" + sharedBikeCompany.getBikeCompanyName() + "公司下的<<" + sharedBike.getBname() + ">>" +
                        ",被借用了" + charge + "秒，支付了" + (charge / 10) + "元  " + now);
                //将单车借出时间修改为空
                sharedBike.setBorrowTime("");
                flag = true;
            }
        }
        //循环完没有完成归还单车的操作时打印的内容
        if (!flag) {
            System.out.println(sharedBikeCompany.getBikeCompanyName() + "公司下没有此单车或还没有被借出");
        }
    }

    //排行榜功能
    private static void rank(ArrayList<SharedBikeCompany> sharedBikeCompanies) {
        ArrayList<SharedBikeCompany> sharedBikeCompaniesNew = (ArrayList<SharedBikeCompany>) sharedBikeCompanies.clone();

        System.out.println("----------->3.排行榜");
        System.out.println("");
        System.out.println("1.按借车次数排行");
        System.out.println("2.按单车总量排行");
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        //按借车次数排行
        if (input == 1) {
            Collections.sort(sharedBikeCompaniesNew, (sharedBikeCompany1, sharedBikeCompany2) -> sharedBikeCompany2.getCount() - sharedBikeCompany1.getCount());
//            for (int i = 1; i < sharedBikeCompaniesNew.size(); i++) {
//                for (int j = 0; j < sharedBikeCompaniesNew.size() - i; j++) {
//                    if (sharedBikeCompaniesNew.get(j).getCount() < sharedBikeCompaniesNew.get(j + 1).getCount()) {
//                        SharedBikeCompany sharedBikeCompany = sharedBikeCompaniesNew.get(j);
//                        sharedBikeCompaniesNew.set(j, sharedBikeCompaniesNew.get(j + 1));
//                        sharedBikeCompaniesNew.set(j + 1, sharedBikeCompany);
//                    }
//                }
//            }
//        按单车总量排行
        } else {
            for (int i = 0; i < sharedBikeCompaniesNew.size() - 1; i++) {
                for (int j = 0; j < sharedBikeCompaniesNew.size() - 1; j++) {
                    if (sharedBikeCompaniesNew.get(j).getSum() < sharedBikeCompaniesNew.get(j + 1).getSum()) {
                        SharedBikeCompany sharedBikeCompany = sharedBikeCompaniesNew.get(j);
                        sharedBikeCompaniesNew.set(j, sharedBikeCompaniesNew.get(j + 1));
                        sharedBikeCompaniesNew.set(j + 1, sharedBikeCompany);
                    }
                }
            }
        }
        System.out.println("公司名称      借车次数     公司单车总量");
        for (SharedBikeCompany sharedBikeCompany : sharedBikeCompaniesNew) {
            System.out.println(sharedBikeCompany.getBikeCompanyName() + "\t\t\t" + sharedBikeCompany.getCount() + "\t\t\t\t" + sharedBikeCompany.getSum());
        }
    }

    //添加单车公司的方法
    private static void addCompany(ArrayList<SharedBikeCompany> sharedBikeCompanies) {
        System.out.println("--------->单车公司管理---->添加单车公司");
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要添加的单车公司(按0返回上一页)");
        String bikeCompanyName = scanner.nextLine();
        //输入0返回
        if ("0".equals(bikeCompanyName)) {
            return;
        }

        SharedBikeCompany sharedBikeCompany = new SharedBikeCompany();
        sharedBikeCompany.setBikeCompanyId(++bikeCompanyId);
        sharedBikeCompany.setBikeCompanyName(bikeCompanyName);
        //将sharedBikeCompany对象添加到sharedBikeCompanies列表中
        sharedBikeCompanies.add(sharedBikeCompany);
        System.out.println("添加" + bikeCompanyName + "公司成功");
        //获取系统当前时间
        String now = df.format(System.currentTimeMillis());
        //将信息存入日志
        log.add("添加" + bikeCompanyName + "公司  " + now);
    }

    //删除单车公司的方法
    private static void deleteCompany(ArrayList<SharedBikeCompany> sharedBikeCompanies) {
        int id = 0;
        System.out.println("--------->单车公司管理---->删除单车公司");
        Scanner scanner = new Scanner(System.in);
        //遍历输出要选择的单车公司名称
        for (SharedBikeCompany sharedBikeCompany : sharedBikeCompanies) {
            System.out.println(++id + "." + sharedBikeCompany.getBikeCompanyName());
        }
        System.out.println("请输入要删除的单车公司编号");
        int bikeCompanyId = scanner.nextInt();
        boolean flag = false;
        //遍历查找每个单车公司，判断是否存在对应编号的单车公司
        for (int i = 0; i < sharedBikeCompanies.size(); i++) {
            SharedBikeCompany sharedBikeCompany = sharedBikeCompanies.get(i);
            if (sharedBikeCompany.getBikeCompanyId() == bikeCompanyId) {
                sharedBikeCompanies.remove(i);
                System.out.println("删除" + sharedBikeCompany.getBikeCompanyName() + "公司成功");
                //获取系统当前时间
                String now = df.format(System.currentTimeMillis());
                //将信息存入日志
                log.add("删除" + sharedBikeCompany.getBikeCompanyName() + "公司  " + now);
                flag = true;
            }
        }
        if (!flag) {
            System.out.println("没有此单车公司，请重新输入");
        }

    }

    //查看单车公司的方法
    private static void findCompany(ArrayList<SharedBikeCompany> sharedBikeCompanies) {
        System.out.println("--------->单车公司管理---->查看单车公司");
        System.out.println("+---------------+---------------+---------------+------------+");
        System.out.println("|     公司序号   |" + "   公司名称   |" + "公司单车数量" + "|公司借出单车次数|");
        System.out.println("+---------------+---------------+---------------+------------+");
        for (SharedBikeCompany sharedBikeCompany : sharedBikeCompanies) {
            System.out.println("       " + sharedBikeCompany.getBikeCompanyId() + "     |     "
                    + sharedBikeCompany.getBikeCompanyName() + "     |     "
                    + sharedBikeCompany.getSum() + "     |     " + sharedBikeCompany.getCount());
            System.out.println("-------------------------------------------------------");
        }
    }

    //查看操作日志方法
    private static void findLog(ArrayList<String> log) {
        System.out.println("*****************操作日志*****************");
        //数据分页
        pagination(log);

//        for (String s : log) {
//            System.out.println(s);
//        }
    }

    //实现分页的方法
    private static void pagination(ArrayList data) {
        if (data.size() == 0 ){
            System.out.println("暂无记录");
            return;
        }
        //当前第几页
        int pageNo = 1;
        //一页五条
        int pageSize = 5;
        //总数
        int total = data.size();
        //总页数
        int pageSum = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
        if (pageSum == 0) {
            pageSum = 1;
        }
        //起始页
        int fromIndex;
        //结束页
        int toIndex;
        //分页
        while (true) {
            //将起始索引设置为当前（页数-1）*条数
            fromIndex = (pageNo - 1) * pageSize;
            //将结束索引设置为起始索引+条数，当起始索引+条数大于总数的时候，将索引设置为总数
            toIndex = Math.min(fromIndex + pageSize, data.size());
            //当在第一页选择上一页时
            if (fromIndex < 0) {
                //将起始索引设置为0
                fromIndex = 0;
                //如果条数小于数据总数时
                if (pageSize < data.size()) {
                    //将结束索引设置为条数
                    toIndex = pageSize;
                } else {
                    //将结束索引设置为数据总数
                    toIndex = data.size();
                }
                //将当前页数加1
                pageNo++;
            }
            //当在最后一页选择下一页时
            if (fromIndex >= data.size()) {
                //将当前页数减1
                pageNo--;
                //将起始索引设置为当前页数减1之后再减1之后*条数
                fromIndex = (pageNo - 1) * pageSize;
            }
            //0-5  5-10
            //输出当前页数的数据
            for (Object s : data.subList(fromIndex, toIndex)) {
                if (s instanceof String) {
                    System.out.println(s);
                }
                if (s instanceof SharedBike) {
                    System.out.println(((SharedBike) s).getBid() + "         " + ((SharedBike) s).getBname() +
                            "     " + (((SharedBike) s).getStatus() == 1 ? "可借" : "已借出") + "  " + ((SharedBike) s).getBorrowTime());
                }
            }
            Scanner scanner = new Scanner(System.in);
            System.out.println("当前第" + pageNo + "页  1.<上一页   2.下一页>   共" + pageSum + "页(按0退出分页)");
            int input = scanner.nextInt();
            switch (input) {
                case 1:
                    pageNo--;
                    break;
                case 2:
                    pageNo++;
                    break;
                case 0:
                    return;
            }
        }
    }
}
