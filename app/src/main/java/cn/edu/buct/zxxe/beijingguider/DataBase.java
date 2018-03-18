package cn.edu.buct.zxxe.beijingguider;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 84978 on 2017/12/30.
 */

public class DataBase {
    public static final String TAG = "DataBase";
    public static final String initFileName = "/data/data/cn.edu.buct.zxxe" +
            ".beijingguider/init.dat";
    private static List<Scene> sceneList;
    private static List<Account> accountList;
    private static List<Route> routeList;

    public static boolean isCreated() {
        try {
            File file = new File(initFileName);
            Log.d(TAG, "isCreated: " + file.exists());
            return file.exists();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean createInitFile() {
        try {
            File file = new File(initFileName);
            boolean isNew = file.createNewFile();
            Log.d(TAG, "createInitFile: " + isNew);
            if (isNew) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Scene> getSceneList() {
        if (sceneList == null)
            sceneList = DataSupport.findAll(Scene.class);
//        for (Scene scene : sceneList) {
//            Log.d(TAG, "getSceneList: " + scene.getName());
//            Log.d(TAG, "getSceneList: " + scene.getDescribeSimple());
//            Log.d(TAG, "getSceneList: " + scene.getImageId());
//        }
        return sceneList;
    }

    public static List<Scene> getNewSceneList(){
        sceneList = DataSupport.findAll(Scene.class);
        return sceneList;
    }

    public static List<Account> getAccountList() {
        if (accountList == null)
            accountList = DataSupport.findAll(Account.class);
        return accountList;
    }

    public static List<Account> getNewAccountList(){
        accountList = DataSupport.findAll(Account.class);
        return accountList;
    }

    public static List<Route> getRouteList() {
        if (routeList == null) {
            routeList = DataSupport.findAll(Route.class);
        }
        return routeList;
    }

    public static List<Route> getNewRouteList(){
        routeList = DataSupport.findAll(Route.class);
        return routeList;
    }

    public static boolean getDataBase() {
//        createDataBase();
        if (isCreated())
            return true;
        if (createInitFile()) {
            createDataBase();
            return true;
        }
        return false;
    }

    public static void deleteAllTable() {
        DataSupport.deleteAll(Scene.class);
        DataSupport.deleteAll(Route.class);
        DataSupport.deleteAll(Account.class);
    }

    public static void createDataBase() {
        SQLiteDatabase dataBase = Connector.getDatabase();
        deleteAllTable();
        createSceneTable();
        createRouteTable();
        createAccountTable();
    }

    public static void restart(){
        getNewAccountList();
        getNewRouteList();
        getNewSceneList();
    }

    public static void createAccountTable() {
        accountList = new ArrayList<>();
        Account account;
        {
            account = new Account();
            account.setUsername("ylzf0000");
            account.setPassword("880210");
            account.save();
            accountList.add(account);
        }
        {
            account = new Account();
            account.setUsername("gejin");
            account.setPassword("123");
            account.save();
            accountList.add(account);
        }
        {
            account = new Account();
            account.setUsername("123");
            account.setPassword("123");
            account.save();
            accountList.add(account);
        }
    }

    public static void createRouteTable() {
        routeList = new ArrayList<>();
        Route route;
        {
            route = new Route();
            route.setImageId(R.drawable.badalingchangcheng);
            route.setTime(7);
            route.setPosition("北京");
            route.setName("长城十三陵一日游");
            route.setDescribe("明十三陵坐落于北京市昌平区天寿山麓，总面积一百二十余平方公里，距离天安门约五十公里。");
            String[] lines = new String[]{"长陵","定陵","长城"};
            route.setLines(lines);
            route.save();
            routeList.add(route);
        }
        {
            route = new Route();
            route.setImageId(R.drawable.changanjie);
            route.setTime(7);
            route.setPosition("北京");
            route.setName("长安街一日游");
            route.setDescribe("长安街，是北京市的一条东西轴线，因位于旧时长安左门、长安右门内而得名，“长安”是中国汉唐国都，有长治久安之意。");
            String[] lines = new String[]{"人民大会堂","天安门","故宫"};
            route.setLines(lines);
            route.save();
            routeList.add(route);
        }
        {
            route = new Route();
            route.setImageId(R.drawable.tiananmen);
            route.setTime(7);
            route.setPosition("北京");
            route.setName("省钱一日穷游北京");
            route.setDescribe("北京，简称“京”，是中华人民共和国的首都、直辖市、国家中心城市、超大城市、国际大都市，全国政治中心、文化中心、国际交往中心、科技创新中心。");
            String[] lines = new String[]{"奥林匹克森林公园","水立方","鸟巢"};
            route.setLines(lines);
            route.save();
            routeList.add(route);
        }
        {
            route = new Route();
            route.setImageId(R.drawable.gongwangfu);
            route.setTime(7);
            route.setPosition("北京");
            route.setName("什刹海恭王府一日游");
            route.setDescribe("什刹海，是北京市历史文化旅游风景区、北京市历史文化保护区。位于市中心城区西城区，毗邻北京城中轴线。");
            String[] lines = new String[]{"鼓楼大街","南锣鼓巷","恭王府"};
            route.setLines(lines);
            route.save();
            routeList.add(route);
        }
        {
            route = new Route();
            route.setImageId(R.drawable.tiananmen);
            route.setTime(7);
            route.setPosition("北京");
            route.setName("漫游北京，感受古都");
            route.setDescribe("北京是首批国家历史文化名城和世界上拥有世界文化遗产数最多的城市，三千多年的历史孕育了故宫、天坛、八达岭长城、颐和园等众多名胜古迹。");
            String[] lines = new String[]{"天坛","毛主席纪念堂","鸟巢","天安门"};
            route.setLines(lines);
            route.save();
            routeList.add(route);
        }
        {
            route = new Route();
            route.setImageId(R.drawable.qijiuba);
            route.setTime(7);
            route.setPosition("北京");
            route.setName("吃货小清新一日游");
            route.setDescribe("王府井大街，南起东长安街，北至中国美术馆，全长约1600米，是北京最有名的商业街。");
            String[] lines = new String[]{"798","南锣鼓巷","王府井"};
            route.setLines(lines);
            route.save();
            routeList.add(route);
        }
    }

    public static void createSceneTable() {
        sceneList = new ArrayList<>();
        Scene scene;
        {
            scene = new Scene();
            scene.setName("故宫");
            scene.setEnglishName("Forbidden city");
            scene.setImageId(R.drawable.gugong);
            scene.setGeography("geo:39.9179756573,116.3971840738");
            scene.setDescribeSimple
                    ("北京故宫是中国明清两代的皇家宫殿，旧称为紫禁城，位于北京中轴线的中心，是中国古代宫廷建筑之精华。北京故宫以三大殿为中心，占地面积72" +
                            "万平方米，建筑面积约15" +
                            "万平方米，有大小宫殿七十多座，房屋九千余间。是世界上现存规模最大、保存最为完整的木质结构古建筑之一。");
            scene.setDescribeComplete
                    ("北京故宫是中国明清两代的皇家宫殿，旧称为紫禁城，位于北京中轴线的中心，是中国古代宫廷建筑之精华。北京故宫以三大殿为中心，占地面积72" +
                            "万平方米，建筑面积约15" +
                            "万平方米，有大小宫殿七十多座，房屋九千余间。是世界上现存规模最大、保存最为完整的木质结构古建筑之一。\n" +
                            "北京故宫于明成祖永乐四年（1406年）开始建设，以南京故宫为蓝本营建，到永乐十八年（1420" +
                            "年）建成。它是一座长方形城池，南北长961" +
                            "米，东西宽753" +
                            "米，四面围有高10米的城墙，城外有宽52米的护城河。紫禁城内的建筑分为外朝和内廷两部分。外朝的中心为太和殿、中和殿、保和殿，统称三大殿，是国家举行大典礼的地方。内廷的中心是乾清宫、交泰殿、坤宁宫，统称后三宫，是皇帝和皇后居住的正宫。[1] \n" +
                            "北京故宫被誉为世界五大宫之首（北京故宫、法国凡尔赛宫、英国白金汉宫、美国白宫、俄罗斯克里姆林宫），是国家AAAAA级旅游景区，[2-3]" +
                            "  " +
                            "1961年被列为第一批全国重点文物保护单位，[4]  1987年被列为世界文化遗产。[5] ");
            scene.save();
            sceneList.add(scene);
        }
        {
            scene = new Scene();
            scene.setName("圆明园");
            scene.setEnglishName("Forbidden city");
            scene.setImageId(R.drawable.yuanmingyuan);
            scene.setGeography("geo:40.0097531766,116.3080379412");
            scene.setDescribeSimple
                    ("圆明园又称圆明三园，是清代一座大型皇家宫苑，它坐落在北京西北郊，与颐和园毗邻，由圆明园、长春园和绮春园组成，所以也叫圆明三园。此外，还有许多小园，分布在东、西、南三面，众星拱月般环绕周围。[1] ");
            scene.setDescribeComplete
                    ("圆明园又称圆明三园，是清代一座大型皇家宫苑，它坐落在北京西北郊，与颐和园毗邻，由圆明园、长春园和绮春园组成，所以也叫圆明三园。此外，还有许多小园，分布在东、西、南三面，众星拱月般环绕周围。[1] \n" +
                            "园林面积350多公顷，建筑面积达20万平方米，一百五十余景，有“万园之园”之称。[2]  " +
                            "清帝每到盛夏就来到这里避暑、听政，处理军政事务，因此也称“夏宫”。\n" +
                            "圆明园始建于1709年（康熙四十八年），最初是康熙帝给皇四子胤禛的赐园。1722年雍正即位以后，拓展原赐园，并在园南增建了正大光明殿和勤政殿以及内阁、六部、军机处诸多值房，欲以夏季在此“避喧听政”。乾隆帝在位期间除对圆明园进行局部增建、改建之外，还在紧东邻新建了长春园，在东南邻并入了万春园。[3]  圆明三园的格局基本形成。嘉庆朝，主要对绮春园（万春园）进行修缮和拓建，使之成为主要园居场所之一。道光帝时，国事日衰，财力不足，但宁撤万寿、香山、玉泉“三山”的陈设，罢热河避暑与木兰狩猎，仍不放弃圆明三园的改建和装饰。1860年10月6日英法联军洗劫圆明园，抢掠文物，焚烧，同治帝时欲修复，后因财政困难，被迫停止，改建其它建筑。八国联军之后，又遭到匪盗的打击，终变成一片废墟。\n" +
                            "圆明园，在清室150余年的创建和经营下，曾以其宏大的地域规模、杰出的营造技艺、精美的建筑景群、丰富的文化收藏和博大精深的民族文化内涵而享誉于世，被誉为“一切造园艺术的典范”，被法国作家维克多雨果称誉为“理想与艺术的典范”。[1]  [5] ");
            scene.save();
            sceneList.add(scene);
        }
        {
            scene = new Scene();
            scene.setName("长城");
            scene.setEnglishName("Forbidden city");
            scene.setImageId(R.drawable.badalingchangcheng);
            scene.setGeography("geo:40.3634179933,116.0265246838");
            scene.setDescribeSimple
                    ("长城（Great Wall），又称万里长城，是中国古代的军事防御工程，是一道高大、坚固而连绵不断的长垣，用以限隔敌骑的行动。长城不是一道单纯孤立的城墙，而是以城墙为主体，同大量的城、障、亭、标相结合的防御体系。");
            scene.setDescribeComplete
                    ("长城（Great Wall），又称万里长城，是中国古代的军事防御工程，是一道高大、坚固而连绵不断的长垣，用以限隔敌骑的行动。长城不是一道单纯孤立的城墙，而是以城墙为主体，同大量的城、障、亭、标相结合的防御体系。\n" +
                            "长城修筑的历史可上溯到西周时期，发生在首都镐京（今陕西西安）的著名的典故“烽火戏诸侯”就源于此。春秋战国时期列国争霸，互相防守，长城修筑进入第一个高潮，但此时修筑的长度都比较短。秦灭六国统一天下后，秦始皇连接和修缮战国长城，始有万里长城之称[1]  。明朝是最后一个大修长城的朝代，今天人们所看到的长城多是此时修筑。[2] \n" +
                            "长城资源主要分布在河北、北京、天津、山西、陕西、甘肃、内蒙古、黑龙江、吉林、辽宁、山东、河南、青海、宁夏、新疆等15" +
                            "个省区市。期中陕西省是中国长城资源最为丰富的省份，境内长城长度达1838千米[3-10]  " +
                            "。根据文物和测绘部门的全国性长城资源调查结果，明长城总长度为8851.8千米，秦汉及早期长城超过1万千米，总长超过2.1万千米。[11]" +
                            " \n" +
                            "1961年3月4日，长城被国务院公布为第一批全国重点文物保护单位[12]  " +
                            "。1987年12月，长城被列入世界文化遗产。[13] ");
            scene.save();
            sceneList.add(scene);
        }
        {
            scene = new Scene();
            scene.setName("颐和园");
            scene.setEnglishName("The Summer Palace");
            scene.setImageId(R.drawable.yiheyuan);
            scene.setGeography("geo:39.9922838245,116.2683448203");
            scene.setDescribeSimple
                    ("颐和园（The Summer Palace），中国清朝时期皇家园林，前身为清漪园，坐落在北京西郊，距城区十五公里，占地约二百九十公顷，与圆明园毗邻。");
            scene.setDescribeComplete
                    ("颐和园（The Summer " +
                            "Palace），中国清朝时期皇家园林，前身为清漪园，坐落在北京西郊，距城区十五公里，占地约二百九十公顷，与圆明园毗邻。它是以昆明湖、万寿山为基址，以杭州西湖为蓝本，汲取江南园林的设计手法而建成的一座大型山水园林，也是保存最完整的一座皇家行宫御苑，被誉为“皇家园林博物馆”，也是国家重点旅游景点。\n" +
                            "清朝乾隆皇帝继位以前，在北京西郊一带，建起了四座大型皇家园林。乾隆十五年（1750年），乾隆皇帝为孝敬其母孝圣皇后动用448" +
                            "万两白银在这里改建为清漪园，形成了从现清华园到香山长达二十公里的皇家园林区。咸丰十年（1860" +
                            "年），清漪园被英法联军焚毁。光绪十四年（1888年）重建，改称颐和园，作消夏游乐地。光绪二十六年（1900" +
                            "年），颐和园又遭“八国联军”的破坏，珍宝被劫掠一空。清朝灭亡后，颐和园在军阀混战和国民党统治时期，又遭破坏。\n" +
                            "1961年3月4日，颐和园被公布为第一批全国重点文物保护单位，与同时公布的承德避暑山庄、拙政园、留园并称为中国四大名园，1998年11" +
                            "月被列入《世界遗产名录》。2007年5月8日，颐和园经国家旅游局正式批准为国家5A级旅游景区。 " +
                            "2009年，颐和园入选中国世界纪录协会中国现存最大的皇家园林。[1]   ");

            scene.save();
            sceneList.add(scene);
        }

        {
            scene = new Scene();
            scene.setName("恭王府");
            scene.setEnglishName("Prince kung’s Mansion");
            scene.setImageId(R.drawable.gongwangfu);
            scene.setGeography("geo:39.9372559540,116.3859921293");
            scene.setDescribeSimple
                    ("恭王府（Prince kung’s " +
                            "Mansion），位于北京市西城区柳荫街，是全国重点文物保护单位，为清代规模最大的一座王府，曾先后作为和珅、永璘的宅邸。");
            scene.setDescribeComplete
                    ("恭王府（Prince kung’s " +
                            "Mansion），位于北京市西城区柳荫街，是全国重点文物保护单位，为清代规模最大的一座王府，曾先后作为和珅、永璘的宅邸。1851" +
                            "年恭亲王奕訢成为宅子的主人，恭王府的名称也因此得来。恭王府规模宏大，占地约6万平方米，分为府邸和花园两部分，拥有各式建筑群落30" +
                            "多处，布局讲究，气派非凡。\n" +
                            "恭王府历经了清王朝由鼎盛而至衰亡的历史进程，承载了极其丰富的历史文化信息，故有了“一座恭王府，半部清代史”的说法。在周恩来、谷牧和李岚清三代国务院领导人的关心下，恭王府腾退修缮工作历28年完成，使之成为当时中国唯一一座对公众开放的清代王府。[1]  \n" +
                            "清室覆亡后，府邸的产权曾归属辅仁大学，八十年代初的恭王府已成为被8家单位割据、数百住户聚居的大杂院，有200" +
                            "余住户。要修复恭王府，首要任务是搬迁。1988年，恭王府花园对外开放，2008年恭王府完成府邸修缮工程后，全面对外开放。\n" +
                            "恭王府位于北京的风水宝地什刹海地区，占地6万多平方米，有“99间半”之称的后罩楼拦腰将之隔为府邸和花园，府邸堂皇庄重，花园优美繁华。在王府、贝勒府扎堆的前后海，恭王府以其富丽而被称作“城中第一佳山水”，更因其堪比故宫的府邸建制而声名显赫。[1]  \n"
                    );
            scene.save();
            sceneList.add(scene);
        }

        {
            scene = new Scene();
            scene.setName("天坛");
            scene.setEnglishName("TianTanPark");
            scene.setImageId(R.drawable.tiantan);
            scene.setGeography("geo:39.8812273482,116.4105388182");
            scene.setDescribeSimple
                    ("天坛（TianTanPark），在北京市南部，东城区永定门内大街东侧。占地约273万平方米。天坛始建于明永乐十八年（1420" +
                            "年），清乾隆、光绪时曾重修改建。");
            scene.setDescribeComplete
                    ("天坛（TianTanPark），在北京市南部，东城区永定门内大街东侧。占地约273万平方米。天坛始建于明永乐十八年（1420" +
                            "年），清乾隆、光绪时曾重修改建。为明、清两代帝王祭祀皇天、祈五谷丰登之场所。天坛是圜丘、祈谷两坛的总称，有坛墙两重，形成内外坛，坛墙南方北圆，象征天圆地方。主要建筑在内坛，圜丘坛在南、祈谷坛在北，二坛同在一条南北轴线上，中间有墙相隔。圜丘坛内主要建筑有圜丘坛、皇穹宇等等，祈谷坛内主要建筑有祈年殿、皇乾殿、祈年门等。  "
                    );
            scene.save();
            sceneList.add(scene);
        }
        {
            scene = new Scene();
            scene.setName("北京大学");
            scene.setEnglishName("Peking University");
            scene.setImageId(R.drawable.beijingdaxue);
            scene.setGeography("geo:39.9928521137,116.3102274812");
            scene.setDescribeSimple
                    ("北京大学，简称“北大”，诞生于1898年，初名京师大学堂，是中国近代第一所国立大学，也是最早以“大学”之名创办的学校，其成立标志着中国近代高等教育的开端。");
            scene.setDescribeComplete
                    ("北京大学，简称“北大”，诞生于1898年，初名京师大学堂，是中国近代第一所国立大学，也是最早以“大学”之名创办的学校，其成立标志着中国近代高等教育的开端。北大是中国近代以来唯一以国家最高学府身份创立的学校，最初也是国家最高教育行政机关，行使教育部职能，统管全国教育。北大催生了中国最早的现代学制，开创了中国最早的文科、理科、社科、农科、医科等大学学科，是近代以来中国高等教育的奠基者。[1]  \n" +
                            "1912年5月3日，京师大学堂改称北京大学校，严复为首任校长。[2]  " +
                            "1916年，蔡元培出任校长，“循思想自由原则、取兼容并包之义”，把北大办成全国学术和思想中心，使北大成为新文化运动中心、五四运动策源地。1937年抗日战争爆发，北大与清华大学、南开大学南迁长沙，组成国立长沙临时大学。不久迁往昆明，改称国立西南联合大学。1946年10月在北平复学。[1]  1952年院系调整，校园从内城沙滩红楼迁至西北郊燕园。[3-4]  \n" +
                            "北大由教育部直属，中央直管副部级建制，是国家双一流[5]  、211工程[1]  、985工程[1]  " +
                            "、2011计划重点建设的全国重点大学；是九校联盟（C9）[6]  及中国大学校长联谊会、亚洲大学联盟[7]  " +
                            "、东亚研究型大学协会、国际研究型大学联盟、环太平洋大学联盟、东亚四大学论坛、国际公立大学论坛、中俄综合性大学联盟[8]  " +
                            "重要成员。[9-11]  \n" +
                            "北大始终与国家民族的命运紧密相连，聚集了许多学者专家，培养了众多优秀人才，创造了大批重大科学成果，影响和推动了中国近现代思想理论、科学技术、文化教育和社会发展的进程。  "
                    );
            scene.save();
            sceneList.add(scene);
        }
        {
            scene = new Scene();
            scene.setName("雍和宫");
            scene.setEnglishName("The Lama Temple");
            scene.setImageId(R.drawable.yonghegong);
            scene.setGeography("geo:39.9476190230,116.4172911626");
            scene.setDescribeSimple
                    ("雍和宫（The Lama Temple）位于北京市区东北角，清康熙三十三年（1694年），康熙帝在此建造府邸、赐予四子雍亲王，称雍亲王府。");
            scene.setDescribeComplete
                    ("雍和宫（The Lama " +
                            "Temple）位于北京市区东北角，清康熙三十三年（1694年），康熙帝在此建造府邸、赐予四子雍亲王，称雍亲王府。雍正三年（1725" +
                            "年），改王府为行宫，称雍和宫。雍正十三年（1735年），雍正驾崩，曾于此停放灵柩，因此，雍和宫主要殿堂原绿色琉璃瓦改为黄色琉璃瓦。又因乾隆皇帝诞生于此，雍和宫出了两位皇帝，成了“龙潜福地”，所以殿宇为黄瓦红墙，与紫禁城皇宫一样规格。乾隆九年（1744年），雍和宫改为喇嘛庙，特派总理事务王大臣管理本宫事务，无定员。可以说，雍和宫是清朝中后期全国规格最高的一座佛教寺院。\n" +
                            "1983年被国务院确定为汉族地区佛教全国重点寺院。该寺院主要由三座精致的牌坊和五进宏伟的大殿组成。从飞檐斗拱的东西牌坊到古色古香东、西顺山楼共占地面积66400平方米，有殿宇千余间。  "
                    );
            scene.save();
            sceneList.add(scene);
        }
        {
            scene = new Scene();
            scene.setName("奥林匹克森林公园");
            scene.setEnglishName("Olympic forest park");
            scene.setImageId(R.drawable.aolinpike);
            scene.setGeography("geo:40.0164548784,116.3915449882");
            scene.setDescribeSimple
                    ("奥林匹克森林公园位于北京市朝阳区北五环林萃路，东至安立路，西至林萃路，北至清河，南至科荟路。");
            scene.setDescribeComplete
                    ("奥林匹克森林公园位于北京市朝阳区北五环林萃路，东至安立路，西至林萃路，北至清河，南至科荟路。公园占地680公顷，其中南园占地380公顷，北园占地300" +
                            "公顷。公园森林资源丰富，以乔灌木为主，绿化覆盖率95.61%。奥林匹克森林公园是奥林匹克公园的终点配套建设项目之一，而奥林匹克公园是国家AAAAA级旅游景区。"
                    );
            scene.save();
            sceneList.add(scene);
        }
        {
            scene = new Scene();
            scene.setName("红螺寺");
            scene.setEnglishName("hongluo temple");
            scene.setImageId(R.drawable.hongluosi);
            scene.setGeography("geo:40.3747151890,116.6252415457");
            scene.setDescribeSimple
                    ("红螺山上红螺寺景区位于北京市怀柔区城北5公里的红螺山南麓，距北京市区55公里，景区总面积800公顷，国家AAAA级旅游区。");
            scene.setDescribeComplete
                    ("红螺山上红螺寺景区位于北京市怀柔区城北5公里的红螺山南麓，距北京市区55公里，景区总面积800公顷，国家AAAA级旅游区。\n" +
                            "红螺寺始建于东晋咸康四年（公元338）年，原名“大明寺”（明正统年间易名“护国资福禅寺”，因红螺仙女的美妙的传说，俗称“红螺寺”）。\n" +
                            "红螺寺坐北朝南，依山势而建，布局严谨，气势雄伟。它背倚红螺山，南照红螺湖，山环水绕，林木丰茂，古树参天。红螺寺处于红螺山山前的千亩苍翠的古松林之中，形成一幅“碧波藏古刹”的优美的画卷。"
                    );
            scene.save();
            sceneList.add(scene);
        }
        {
            scene = new Scene();
            scene.setName("国子监");
            scene.setEnglishName("The Imperial College ");
            scene.setImageId(R.drawable.guozijian);
            scene.setGeography("geo:39.9469352007,116.4133305326");
            scene.setDescribeSimple
                    ("国子监是元、明、清三代国家设立的最高学府和教育行政管理机构，又称“太学”“国学”。");
            scene.setDescribeComplete
                    ("国子监是元、明、清三代国家设立的最高学府和教育行政管理机构，又称“太学”“国学”。\n" +
                            "它始建于元代至元二十四年(公元1287年)" +
                            "，明代永乐，正统年间曾大规模修葺和扩建，清乾隆四十八年又增建“辟雍”一组皇家建筑，形成现在的规制。国子监整体建筑坐北朝南，为三进院落，占地面积二万七千多平方米。中轴线上依次排列着集贤门(大门)、太学门(二门)、琉璃牌坊、辟雍殿、彝伦堂、敬一亭。\n" +
                            "古代在国子监读书的学生称为“监生”。国子监不仅接纳全国各族学生，还接待外国留学生，为培养国内各民族人才，促进中外文化交流，曾起到积极的作用。国子监主体建筑经历700多年依然保存完好，是唯一保存完整的古代最高学府校址，国子监以其悠久的历史，独特的建筑风貌，深厚的文化内涵而闻名于世。[1]  "
                    );
            scene.save();
            sceneList.add(scene);
        }
        {
            scene = new Scene();
            scene.setName("王府井");
            scene.setEnglishName("Wang Fu Jing");
            scene.setImageId(R.drawable.wangfujing);
            scene.setGeography("geo:39.9111784904,116.4113938382");
            scene.setDescribeSimple
                    ("王府井大街，南起东长安街，北至中国美术馆，全长约1600米，是北京最有名的商业街。");
            scene.setDescribeComplete
                    ("王府井大街，南起东长安街，北至中国美术馆，全长约1600" +
                            "米，是北京最有名的商业街。王府井的日用百货、五金电料、服装鞋帽、珠宝钻石、金银首饰等，琳琅满目，商品进销量极大，是号称“日进斗金”的寸金之地。\n" +
                            "2012年9月，王府井建设管理办公室工作人员透露，王府井大街5年内将扩容40%。 "
                    );
            scene.save();
            sceneList.add(scene);
        }
        {
            scene = new Scene();
            scene.setName("凤凰岭");
            scene.setEnglishName("FengHuangMountain ");
            scene.setImageId(R.drawable.fenghuangling);
            scene.setGeography("geo:40.1072990704,116.1051650009");
            scene.setDescribeSimple
                    ("北京凤凰岭自然风景区位于海淀区苏家坨镇境内，距天安门33公里，总面积10.62平方公里。");
            scene.setDescribeComplete
                    ("北京凤凰岭自然风景区位于海淀区苏家坨镇境内，距天安门33公里，总面积10.62" +
                            "平方公里。青山绿水，蓝天白云，层峦叠翠，密林曲径，奇花异草遍及山野，具有良好的生态环境；其上风上水的地理优势，使之享有京城“绿肺”之称。"
                    );
            scene.save();
            sceneList.add(scene);
        }
        {
            scene = new Scene();
            scene.setName("丫髻山");
            scene.setEnglishName("YaJimountain ");
            scene.setImageId(R.drawable.yajishan);
            scene.setGeography("geo:40.2730674570,116.9811221466");
            scene.setDescribeSimple
                    ("丫髻山风景区位于北京市平谷区刘家店镇（距天安门91公里，据东直门70公里，距金海湖45公里。）因山巅两块巨石状若古代女孩头上的丫髻，因此得名丫髻山。");
            scene.setDescribeComplete
                    ("丫髻山风景区位于北京市平谷区刘家店镇（距天安门91公里，据东直门70公里，距金海湖45" +
                            "公里。）因山巅两块巨石状若古代女孩头上的丫髻，因此得名丫髻山。四方百姓又称丫髻山为“东大山”。丫髻山海拔363" +
                            "米，丫髻山上的碧霞元君祠为京东著名道观，始建于唐代鼎盛于元、明、清三朝；丫髻山磕头沟原有云岩寺，分上下两寺，始建于辽代，后经多次重修。上寺建筑悬崖峭壁上，其规模之大，工程之险，古来罕见。"
                    );
            scene.save();
            sceneList.add(scene);
        }

        {
            scene = new Scene();
            scene.setName("大观园");
            scene.setEnglishName("DaGuanYuan ");
            scene.setImageId(R.drawable.daguanyuan);
            scene.setGeography("geo:39.8714085256,116.3564516563");
            scene.setDescribeSimple
                    ("北京大观园是一座再现中国古典文学名著《红楼梦》中“大观园”景观的仿古园林，位于宣武区南菜园（市区西南隅护城河畔）。");
            scene.setDescribeComplete
                    ("北京大观园是一座再现中国古典文学名著《红楼梦》中“大观园”景观的仿古园林，位于宣武区南菜园（市区西南隅护城河畔）。原址为明清两代皇家菜园，明代曾在此设“嘉疏署”。1984年为拍摄电视剧《红楼梦》，经红学家、古建筑家、园林学家和清史专家共同商讨，按作者在书中的描述，采用中国古典建筑的技法和传统的造园艺术手法建造。园中的园林建筑、山形水系、植物造景、小品点缀等，均力图忠实于原著的时代风尚和细节描写，《红楼梦》中的大观园是为贾府大小姐元春而建的省亲别墅，因此园中定期举办“元妃省亲”古装表演。\n" +
                            "大观园总面积12.5公顷，建筑面积8千多平方米，开辟水系24000平方米，堆山叠石6" +
                            "万土石方。全园有庭院景区五处、自然景区三处、佛寺景区一处、殿宇景区一处，共有景点四十多个。大观园在每年农历春节初一至初六举办“红楼庙会”。庙会内容包括：文艺演出、民间花会、风味小吃、民俗活动等。其中“元妃省亲”古装巡游是大观园文化庙会的传统项目和独有的特色。每年农历八月十五期间还举办“北京大观园‘中秋之夜’”。活动以文艺演出、赏月团聚、观赏夜景为内容，每届举办3至4天，是京城中秋活动的传统品牌项目。"
                    );
            scene.save();
            sceneList.add(scene);
        }
        {
            scene = new Scene();
            scene.setName("地坛");
            scene.setEnglishName("DiTan ");
            scene.setImageId(R.drawable.ditan);
            scene.setGeography("geo:39.9542290230,116.4154811626");
            scene.setDescribeSimple
                    ("地坛公园又称方泽坛，是古都北京五坛中的第二大坛。地坛公园位于北京市东城区安定门外大街，占地37.4公顷。");
            scene.setDescribeComplete
                    ("地坛公园又称方泽坛，是古都北京五坛中的第二大坛。地坛公园位于北京市东城区安定门外大街，占地37.4公顷。公园始建于明代嘉靖九年（公元1530" +
                            "年），是明清两朝帝王祭祀“皇地祇神”的场所，也是中国现存的最大的祭地之坛。地坛公园有方泽坛、皇祇室、牌楼、斋宫等著名旅游景点。1984" +
                            "年被评为北京市文物保护单位。[1-2]  "
                    );
            scene.save();
            sceneList.add(scene);
        }
        {
            scene = new Scene();
            scene.setName("798");
            scene.setEnglishName("798 ");
            scene.setImageId(R.drawable.qijiuba);
            scene.setGeography("geo:39.9840489428,116.4943027121");
            scene.setDescribeSimple
                    ("798艺术区（ArtDist）位于北京朝阳区酒仙桥街道大山子地区，故又称大山子艺术区（英文简称DAD－Dashanzi Art " +
                            "District），原为原国营798厂等电子工业的老厂区所在地。");
            scene.setDescribeComplete
                    ("798艺术区（ArtDist）位于北京朝阳区酒仙桥街道大山子地区，故又称大山子艺术区（英文简称DAD－Dashanzi Art " +
                            "District），原为原国营798厂等电子工业的老厂区所在地。如今798已经引起了国内外媒体和大众的广泛关注，成为了北京都市文化的新地标。\n" +
                            "此区域西起酒仙桥路，东至酒仙桥东路、北起酒仙桥北路，南至将台路，面积60多万平方米，因当代艺术和798" +
                            "生活方式闻名于世。艺术区的名字是由北京国营电子工业老厂区的名称沿用而来。在798艺术区的发展过程中形成了798共识，因此，798" +
                            "也指这一艺术区引申出的一种文化概念，以及LOFT这种时尚的居住与工作方式，简称798生活方式或798方式。 "
                    );
            scene.save();
            sceneList.add(scene);
        }
        {
            scene = new Scene();
            scene.setName("戒台寺");
            scene.setEnglishName("JieTaiTemple ");
            scene.setImageId(R.drawable.jietaisi);
            scene.setGeography("geo:39.8695608067,116.0871943256");
            scene.setDescribeSimple
                    ("戒台寺位于北京市门头沟区的马鞍山上，始建于唐武德五年（公元622" +
                            "年），原名慧聚寺，明朝英宗皇帝赐名为万寿禅寺，因寺内建有全国最大的佛教戒坛，民间通称为戒坛寺，又叫戒台寺。");
            scene.setDescribeComplete
                    ("戒台寺位于北京市门头沟区的马鞍山上，始建于唐武德五年（公元622" +
                            "年），原名慧聚寺，明朝英宗皇帝赐名为万寿禅寺，因寺内建有全国最大的佛教戒坛，民间通称为戒坛寺，又叫戒台寺。\n" +
                            "戒台寺是全国重点文物保护单位，它是中国北方保存辽代文物最多、最完整的寺院。最特别的其是保留了佛塔，经幢、戒坛等辽代佛教中十分罕见的珍品。[1]  \n" +
                            "2014年12月18日，北京门头沟区责令古寺景区撤除违规功德箱。 "
                    );
            scene.save();
            sceneList.add(scene);
        }
        {
            scene = new Scene();
            scene.setName("碧云寺");
            scene.setEnglishName("BiiYunTemple ");
            scene.setImageId(R.drawable.biyunsi);
            scene.setGeography("geo:39.9969764612,116.1933020696");
            scene.setDescribeSimple
                    ("碧云寺位于北京海淀区香山公园北侧，西山余脉聚宝山东麓，是一组布局紧凑、保存完好的园林式寺庙。创建于元至顺二年（1331年），后经明、清扩建。");
            scene.setDescribeComplete
                    ("碧云寺位于北京海淀区香山公园北侧，西山余脉聚宝山东麓，是一组布局紧凑、保存完好的园林式寺庙。创建于元至顺二年（1331年），后经明、清扩建。\n" +
                            "寺院坐西朝东，依山势而建造。整个寺院布置，以排列在六进院落为主体，南北各配一组院落，院落采用各自封闭建筑手法，层层殿堂依山叠起，三百多级阶梯式地势而形成的特殊布局。因寺院依山势逐渐高起，为不使总体布局景露无遗，故而采用迥旋串连引人入胜的建造形式。其中立于山门前的一对石狮、哼哈二将，殿中的泥质彩塑以及弥勒佛殿山墙上的壁塑皆为明代艺术珍品。[1]  "
                    );
            scene.save();
            sceneList.add(scene);
        }
        {
            scene = new Scene();
            scene.setName("北海公园");
            scene.setEnglishName("Beihai Park ");
            scene.setImageId(R.drawable.beihaigongyuan);
            scene.setGeography("geo:39.9258225766,116.3892557049");
            scene.setDescribeSimple
                    ("北海公园(Beihai Park)，位于北京市中心区，城内景山西侧，在故宫的西北面，与中海、南海合称三海。属于中国古代皇家园林。");
            scene.setDescribeComplete
                    ("北海公园(Beihai Park)" +
                            "，位于北京市中心区，城内景山西侧，在故宫的西北面，与中海、南海合称三海。属于中国古代皇家园林。全园以北海为中心，面积约71" +
                            "公顷，水面占583市亩，陆地占480市亩。这里原是辽、金、元建离宫，明、清辟为帝王御苑，是中国现存最古老、最完整、最具综合性和代表性的皇家园林之一，1925年开放为公园。是中国保留下来的最悠久最完整的皇家园林，为全国重点文物保护单位，是国家AAAA级旅游景区。  "
                    );
            scene.save();
            sceneList.add(scene);
        }
        {
            scene = new Scene();
            scene.setName("香山");
            scene.setEnglishName("The Xiangshan Park  ");
            scene.setImageId(R.drawable.xiangshan);
            scene.setGeography("geo:39.99009,116.19092");
            scene.setDescribeSimple
                    ("香山公园位于北京西郊，地势险峻，苍翠连绵，占地188公顷，是一座具有山林特色的皇家园林。");
            scene.setDescribeComplete
                    ("香山公园位于北京西郊，地势险峻，苍翠连绵，占地188公顷，是一座具有山林特色的皇家园林。景区内主峰香炉峰俗称“鬼见愁”，海拔557" +
                            "米。香山公园始建于金大定二十六年，距今已有近900年的历史。香山公园有香山寺、洪光寺等著名旅游景点。 " +
                            "香山公园于1993年被评为首都文明单位，2001年被国家旅游局评为AAAA景区，2002年被评为首批北京市精品公园。  "
                    );
            scene.save();
            sceneList.add(scene);
        }
        {
            scene = new Scene();
            scene.setName("清华大学");
            scene.setEnglishName("Tsinghua University  ");
            scene.setImageId(R.drawable.qinghuadaxue);
            scene.setGeography("geo:40.00281,116.32697");
            scene.setDescribeSimple
                    ("清华大学（Tsinghua University），简称“清华”，由中华人民共和国教育部直属，中央直管副部级建制，位列“211工程”、“985" +
                            "工程”、“世界一流大学和一流学科”，入选“基础学科拔尖学生培养试验计划”、“高等学校创新能力提升计划”、“高等学校学科创新引智计划”，为九校联盟、中国大学校长联谊会、东亚研究型大学协会、亚洲大学联盟、环太平洋大学联盟、清华—剑桥—MIT低碳大学联盟成员，被誉为“红色工程师的摇篮”。");
            scene.setDescribeComplete
                    ("清华大学（Tsinghua University），简称“清华”，由中华人民共和国教育部直属，中央直管副部级建制，位列“211工程”、“985" +
                            "工程”、“世界一流大学和一流学科”，入选“基础学科拔尖学生培养试验计划”、“高等学校创新能力提升计划”、“高等学校学科创新引智计划”，为九校联盟、中国大学校长联谊会、东亚研究型大学协会、亚洲大学联盟、环太平洋大学联盟、清华—剑桥—MIT低碳大学联盟成员，被誉为“红色工程师的摇篮”。\n" +
                            "清华大学的前身清华学堂始建于1911年，因水木清华而得名，是清政府设立的留美预备学校，其建校的资金源于1908" +
                            "年美国退还的部分庚子赔款。1912年更名为清华学校。1928年更名为国立清华大学。1937" +
                            "年抗日战争全面爆发后南迁长沙，与北京大学、南开大学组建国立长沙临时大学，1938年迁至昆明改名为国立西南联合大学。1946" +
                            "年迁回清华园。1949年中华人民共和国成立，清华大学进入了新的发展阶段。1952年全国高等学校院系调整后成为多科性工业大学。1978" +
                            "年以来逐步恢复和发展为综合性的研究型大学。\n" +
                            "水木清华，钟灵毓秀，清华大学秉持“自强不息、厚德载物”的校训和“行胜于言”的校风，坚持“中西融汇、古今贯通、文理渗透”的办学风格和“又红又专、全面发展”的培养特色，弘扬“爱国奉献、追求卓越”传统和“人文日新”精神。恰如清华园工字厅内对联所书——“槛外山光，历春夏秋冬、万千变幻，都非凡境；窗中云影，任东西南北、去来澹荡，洵是仙居”。  "
                    );
            scene.save();
            sceneList.add(scene);
        }
        {
            scene = new Scene();
            scene.setName("景山公园");
            scene.setEnglishName("Jingshan Park");
            scene.setImageId(R.drawable.jingshangongyuan);
            scene.setGeography("geo:39.92576000000001,116.39663999999998");
            scene.setDescribeSimple
                    ("景山公园位于北京市西城区景山前街，坐落在明清北京城的中轴线上，西临北海，南与故宫神武门隔街相望，是明、清两代的御苑。公园中心的景山为堆土而成，曾是全城的制高点。");
            scene.setDescribeComplete
                    ("景山公园位于北京市西城区景山前街，坐落在明清北京城的中轴线上，西临北海，南与故宫神武门隔街相望，是明、清两代的御苑。公园中心的景山为堆土而成，曾是全城的制高点。\n" +
                            "景山公园1928年建园，全园总面积23公顷其中，花卉草坪占地1100 平方米。该公园是国家AAAA级旅游景区，[1]  " +
                            "公园内有绮望楼、五方亭、寿皇殿、永思殿、牡丹园等景点。 "
                    );
            scene.save();
            sceneList.add(scene);
        }
        {
            scene = new Scene();
            scene.setName("长陵");
            scene.setEnglishName("ChangLing");
            scene.setImageId(R.drawable.changling);
            scene.setGeography("geo:40.29798000000002,116.24884999999999");
            scene.setDescribeSimple
                    ("明长陵为明十三陵之首，是明成祖朱棣和皇后徐氏的合葬墓，位于北京市昌平区天寿山主峰南麓。");
            scene.setDescribeComplete
                    ("明长陵为明十三陵之首，是明成祖朱棣和皇后徐氏的合葬墓，位于北京市昌平区天寿山主峰南麓。明长陵建于永乐七年（1409年），在十三陵中建筑规模最大，营建时间最早，陵园规模宏大，用料严格考究，施工精细，工程浩繁，营建时日旷久，仅地下宫殿就历时四年。地面建筑也保存得最为完好。[1]  \n" +
                            "长陵的陵宫建筑占地约12万平方米。其平面布局呈前方后圆形状。其前面的方形部分，由前后相连的三进院落组成。它是十三陵中的祖陵，也是陵区内最主要的旅游景点之一。1961年被公布为全国重点文物保护单位。 "
                    );
            scene.save();
            sceneList.add(scene);
        }
        {
            scene = new Scene();
            scene.setName("陶然亭");
            scene.setEnglishName("Leasure Pavillion Park ");
            scene.setImageId(R.drawable.taoranting);
            scene.setGeography("geo:39.875480000000024,116.38266000000002");
            scene.setDescribeSimple
                    ("北京陶然亭公园位于北京市西城区，建于1952年，是一座融古典建筑和现代造园艺术为一体的以突出中华民族“亭文化”为主要内容的历史文化名园，是AAAA" +
                            "景区（点），北京市精品公园。");
            scene.setDescribeComplete
                    ("北京陶然亭公园位于北京市西城区，建于1952年，是一座融古典建筑和现代造园艺术为一体的以突出中华民族“亭文化”为主要内容的历史文化名园，是AAAA" +
                            "景区（点），北京市精品公园。全园占地面积56.56公顷，其中水域面积为16.15公顷。陶然亭是清代名亭，现为中国四大历史名亭之一。陶然亭公园以及陶然亭地区名称就是以此亭而得名的。陶然亭公园是建国后，北京市政府最早兴建的一座现代园林，名闻遐迩的陶然亭、慈悲庵等建筑就坐落在这里。"
                    );
            scene.save();
            sceneList.add(scene);
        }
        {
            scene = new Scene();
            scene.setName("长安街");
            scene.setEnglishName("Chang An Avenue");
            scene.setImageId(R.drawable.changanjie);
            scene.setGeography("geo:40.37405155468331,116.84433");
            scene.setDescribeSimple
                    ("长安街，是北京市的一条东西轴线，因位于旧时长安左门、长安右门内而得名，“长安”是中国汉唐国都，有长治久安之意。东长安门以东到东单，称为东长安街；西长安门以西到西单，称为西长安街。");
            scene.setDescribeComplete
                    ("长安街，是北京市的一条东西轴线，因位于旧时长安左门、长安右门内而得名，“长安”是中国汉唐国都，有长治久安之意。东长安门以东到东单，称为东长安街；西长安门以西到西单，称为西长安街。\n" +
                            "天安门坐落于长安街中点的北侧，天安门广场则在其南侧。长安街曾被认为是世界上最长、最宽的街道，也是中国最重要的一条街道之一，在中国有人认为是“神州第一街”。长安街是国内最重要的一条街道，阅兵仪式都会在这里举行。\n" +
                            "明永乐十八年(1420年)" +
                            "，明成祖朱棣迁都北京的时候，皇家就精心地在皇城的正前方安排了这样一条东西走向的横街。旧时的长安街是封建统治的中心。过去称长安街是指从东单至西单，长度为7.4华里。通常说的“十里长街”，则是指建国门至复兴门的距离，长为13.4华里。所说的“百里长街”是指通州至石景山的距离，全长84华里。所谓的“十里”和“百里”均是泛称，并不是确切的数字。"
                    );
            scene.save();
            sceneList.add(scene);
        }
        {
            scene = new Scene();
            scene.setName("大栅栏");
            scene.setEnglishName("Dashilanr");
            scene.setImageId(R.drawable.dazhalan);
            scene.setGeography("geo:39.895600000000016,116.39572");
            scene.setDescribeSimple
                    ("大栅栏（Dashilanr），是北京市前门外一条著名的商业街。现也泛指大栅栏街及廊房头条、粮食店街、煤市街在内的一个地片。");
            scene.setDescribeComplete
                    ("大栅栏（Dashilanr），是北京市前门外一条著名的商业街。现也泛指大栅栏街及廊房头条、粮食店街、煤市街在内的一个地片。大栅栏地处古老北京中心地段，是南中轴线的一个重要组成部分，位于天安门广场以南，前门大街西侧，从东口至西口全长275米。自1420年（明朝永乐十八年）以来，经过500多年的沿革，逐渐发展成为店铺林立的商业街了。\n" +
                            "在大栅栏分布着11个行业的36家商店。复原民国初期风貌，大栅栏已有500多年的历史，曾经就是一个繁华的商业区。兴起于元代，建立于明朝，从清代开始繁盛至今。1900年义和团曾一把火将整条街付之一炬，重建后依旧繁华。据中国社会科学院考古研究所专家徐萍芳介绍，此次复原以民国初期风貌为基础。大栅栏的由来，要追溯到明代孝宗弘治元年。当时，北京有“宵禁”，为了防止盗贼隐藏在大街小巷之内，由朝廷批准，在北京很多街巷道口，建立了木栅栏。"
                    );
            scene.save();
            sceneList.add(scene);
        }
        {
            scene = new Scene();
            scene.setName("鼓楼大街");
            scene.setEnglishName("Gu Lou Avenue");
            scene.setImageId(R.drawable.gulou);
            scene.setGeography("geo:39.949239196404065,116.393599");
            scene.setDescribeSimple
                    ("鼓楼位于北京市中轴线上、鼓楼东大街与地安门外大街交会处。与钟楼一起，元、明、清时为北京全城报时中心。");
            scene.setDescribeComplete
                    ("鼓楼位于北京市中轴线上、鼓楼东大街与地安门外大街交会处。与钟楼一起，元、明、清时为北京全城报时中心。北京鼓楼大街古朴厚重，两旁分布着四通八达的胡同、许多老字号商店与各种老北京小吃，而如今的鼓楼大街已逐渐弥漫着时尚的气息，古典与现代的融合使鼓楼大街焕发新的生机。"
                    );
            scene.save();
            sceneList.add(scene);
        }
        {
            scene = new Scene();
            scene.setName("国家大剧院");
            scene.setEnglishName("National Centre for the Performing Arts");
            scene.setImageId(R.drawable.guojiadajuyuan);
            scene.setGeography("geo:39.90472999999999,116.38979999999998");
            scene.setDescribeSimple
                    ("中国国家大剧院位于北京市中心天安门广场西侧，中国国家表演艺术的最高殿堂，中外文化交流的最大平台，中国文化创意产业的重要基地 。");
            scene.setDescribeComplete
                    ("中国国家大剧院位于北京市中心天安门广场西侧，中国国家表演艺术的最高殿堂，中外文化交流的最大平台，中国文化创意产业的重要基地 。\n" +
                            "从国家大剧院第一次立项到正式运营，经历了49年，设计方案经历了三次竞标两次修改，总造价30.67" +
                            "亿元。由法国建筑师保罗·安德鲁主持设计，设计方为法国巴黎机场公司。占地11.89万平方米，总建筑面积约16.5" +
                            "万平方米，其中主体建筑10.5万平方米，地下附属设施6万平方米。设有歌剧院、音乐厅、戏剧场以及艺术展厅、艺术交流中心、音像商店等配套设施。\n" +
                            "作为新北京十六景之一的地标性建筑，国家大剧院造型独特的主体结构，一池清澈见底的湖水，以及外围大面积的绿地、树木和花卉，不仅极大改善了周围地区的生态环境，更体现了人与人、人与艺术、人与自然和谐共融、相得益彰的理念。"
                    );
            scene.save();
            sceneList.add(scene);
        }
        {
            scene = new Scene();
            scene.setName("卢沟桥");
            scene.setEnglishName("Lugou Bridge");
            scene.setImageId(R.drawable.lugouqiao);
            scene.setGeography("geo:39.85136999999999,116.22626999999999");
            scene.setDescribeSimple
                    ("卢沟桥亦称芦沟桥，在北京市西南约15公里处，丰台区永定河上。因横跨卢沟河（即永定河）而得名，是北京市现存最古老的石造联拱桥。");
            scene.setDescribeComplete
                    ("卢沟桥亦称芦沟桥，在北京市西南约15公里处，丰台区永定河上。因横跨卢沟河（即永定河）而得名，是北京市现存最古老的石造联拱桥。\n" +
                            "卢沟桥为十一孔联拱桥，拱洞由两岸向桥中心逐渐增大，拱券跨径从12.35米至13.42米不等，桥身中央微微突起93.5" +
                            "厘米，坡势平缓。河面桥长213.15米，加上两端的引桥，总长266.5米。桥身总宽9.3米。桥面宽7.5" +
                            "米。桥两侧雁翅桥面呈喇叭口状，入口处宽32米。桥面两侧设置石栏，南侧有望柱140根，北侧有141根。望柱间距约1.8米至2米，柱高1.4" +
                            "米。柱间各嵌石栏板，栏高约0.85米。\n" +
                            "整个桥身都是石体结构，关键部位均有银锭铁榫连接，为华北最长的古代石桥。在《马可·波罗游记》中它被形容为一座巨丽的石桥，后来外国人都称它为\"马可波罗桥\"  。1937年7月7日，日本帝国主义在此发动全面侵华战争。宛平城的中国驻军奋起抵抗，史称“卢沟桥事变”（亦称“七七事变”）。 中国抗日军队在卢沟桥打响了全面抗战的第一枪。"
                    );
            scene.save();
            sceneList.add(scene);
        }
        {
            scene = new Scene();
            scene.setName("毛主席纪念堂");
            scene.setEnglishName("Chairman Mao Zedong Memorial Hall ");
            scene.setImageId(R.drawable.maozhuxijiniantang);
            scene.setGeography("geo:39.902500000000025,116.39775000000002");
            scene.setDescribeSimple
                    ("毛主席纪念堂是为纪念开国领袖毛泽东而建造的，位于天安门广场， " +
                            "人民英雄纪念碑南面，坐落在原中华门旧址。1976年11月24日按照中国共产党中央委员会的决议，毛主席纪念堂奠基仪式在天安门广场举行。");
            scene.setDescribeComplete
                    ("毛主席纪念堂是为纪念开国领袖毛泽东而建造的，位于天安门广场， " +
                            "人民英雄纪念碑南面，坐落在原中华门旧址。1976年11月24日按照中国共产党中央委员会的决议，毛主席纪念堂奠基仪式在天安门广场举行。\n" +
                            "毛主席纪念堂于1977年5月24日落成，占地57000多平方米，总建筑面积为28000平方米。主体呈正方形，外有44" +
                            "根福建黄色花岗石建筑的明柱，柱间装有广州石湾花饰陶板，通体青岛花岗石贴面。屋顶有两层玻璃飞檐，檐间镶葵花浮雕。基座有两层平台，台帮全部用四川大渡河旁的枣红色花岗石砌成，四周环以房山汉白玉万年青花饰栏杆。"
                    );
            scene.save();
            sceneList.add(scene);
        }
        {
            scene = new Scene();
            scene.setName("南锣鼓巷");
            scene.setEnglishName("South Luogu Lane");
            scene.setImageId(R.drawable.nanluoguxiang);
            scene.setGeography("geo:39.93347725703949,116.40302799999998");
            scene.setDescribeSimple
                    ("南锣鼓巷是一条胡同，位于北京中轴线东侧的交道口地区，北起鼓楼东大街，南至平安大街，宽8米，全长787米，于元大都同期建成。");
            scene.setDescribeComplete
                    ("南锣鼓巷是一条胡同，位于北京中轴线东侧的交道口地区，北起鼓楼东大街，南至平安大街，宽8米，全长787" +
                            "米，于元大都同期建成。是北京最古老的街区之一，至今已有740多年的历史。也位列规划中的25" +
                            "片旧城保护区之中。因其地势中间高、南北低，如一驼背人，故名罗锅巷。到了清朝，乾隆十五年（1750年）绘制的《京城全图》改称南锣鼓巷。\n" +
                            "它是北京最古老的街区之一，是我国唯一完整保存着元代胡同院落肌理、规模最大、品级最高、资源最丰富的棋盘式传统民居区，也是最赋有老北京风情的街巷。周边胡同里各种形制的府邸、宅院多姿多彩，厚重深邃。南锣鼓巷及周边区域曾是元大都的市中心，明清时期则更是一处大富大贵之地，这里的街街巷巷挤满了达官显贵，王府豪庭数不胜数，直到清王朝覆灭后，南锣鼓巷的繁华也跟着慢慢落幕。"
                    );
            scene.save();
            sceneList.add(scene);
        }
        {
            scene = new Scene();
            scene.setName("前门");
            scene.setEnglishName("Front Gate");
            scene.setImageId(R.drawable.qianmen);
            scene.setGeography("geo:39.9001,116.39794000000002");
            scene.setDescribeSimple
                    ("正阳门，俗称前门、前门楼子、大前门，原名丽正门，是明清两朝北京内城的正南门。");
            scene.setDescribeComplete
                    ("正阳门，俗称前门、前门楼子、大前门，原名丽正门，是明清两朝北京内城的正南门。位于北京城南北中轴线上的天安门广场最南端，毛主席纪念堂南边。始建于明成祖永乐十七年（1419年），是老北京“京师九门”之一。它集正阳门城楼、箭楼与瓮城为一体，是一座完整的古代防御性建筑体系。 据地方志上记载：当时的城楼、箭楼规模宏丽，形制高大；瓮城气势雄浑，为老北京城垣建筑的代表之作，现仅存城楼和箭楼，是北京城内唯一保存较完整的城门。城楼上有北京民俗展览馆。"
                    );
            scene.save();
            sceneList.add(scene);
        }
        {
            scene = new Scene();
            scene.setName("人民大会堂");
            scene.setEnglishName("The Great Hall of the People");
            scene.setImageId(R.drawable.renmindahuitang);
            scene.setGeography("geo:39.90517,116.39381999999998");
            scene.setDescribeSimple
                    ("中华人民共和国中央政府人民大会堂位于中国北京市天安门广场西侧，西长安街南侧。人民大会堂坐西朝东，南北长336米，东西宽206米，高46.5" +
                            "米，占地面积15万平方米，建筑面积17.18万平方米。");
            scene.setDescribeComplete
                    ("中华人民共和国中央政府人民大会堂位于中国北京市天安门广场西侧，西长安街南侧。人民大会堂坐西朝东，南北长336米，东西宽206米，高46.5" +
                            "米，占地面积15万平方米，建筑面积17.18万平方米。\n" +
                            "人民大会堂是中国全国人民代表大会开会地和全国人民代表大会常务委员会的办公场所，是党、国家和各人民团体举行政治活动的重要场所，也是中国党和国家领导人和人民群众举行政治、外交、文化活动的场所。人民大会堂每年举行的全国人民代表大会、中国人民政治协商会议以及五年一届的中国共产党全国代表大会也在此召开。"
                    );
            scene.save();
            sceneList.add(scene);
        }
        {
            scene = new Scene();
            scene.setName("人民英雄纪念碑");
            scene.setEnglishName("Monument to the People's Heroes");
            scene.setImageId(R.drawable.renminyingxiongjinianbei);
            scene.setGeography("geo:39.904560000000004,116.39769999999999");
            scene.setDescribeSimple
                    ("人民英雄纪念碑（Monument to the People's " +
                            "Heroes）位于北京天安门广场中心，在天安门南约463米，正阳门北约440米的南北中轴线上，是中华人民共和国政府为纪念中国近现代史上的革命烈士而修建的纪念碑。");
            scene.setDescribeComplete
                    ("人民英雄纪念碑（Monument to the People's " +
                            "Heroes）位于北京天安门广场中心，在天安门南约463米，正阳门北约440米的南北中轴线上，是中华人民共和国政府为纪念中国近现代史上的革命烈士而修建的纪念碑。\n" +
                            "1949年9月30日，中国人民政治协商会议第一届全体会议决定，为了纪念在人民解放战争和人民革命中牺牲的人民英雄，在首都北京建立人民英雄纪念碑。1949年9月30日奠基，1952年8月1日开工，1958年4月22日建成[1]  ，1958年5月1日揭幕，1961年被中华人民共和国国务院公布为第一批全国重点文物保护单位之一。\n" +
                            "人民英雄纪念碑通高37.94米，正面（北面）碑心是一整块石材，长14.7米、宽2.9米、厚1米、重60.23吨，镌刻着毛泽东同志1955" +
                            "年6月9日所题写的“人民英雄永垂不朽”八个金箔大字。背面碑心由7块石材构成，内容为毛泽东起草、周恩来书写的150字小楷字体碑文。"
                    );
            scene.save();
            sceneList.add(scene);
        }
        {
            scene = new Scene();
            scene.setName("水立方");
            scene.setEnglishName("Water Cube");
            scene.setImageId(R.drawable.shuilifang);
            scene.setGeography("geo:39.99300000000001,116.39051");
            scene.setDescribeSimple
                    ("国家游泳中心又称“水立方”(Water Cube)" +
                            "位于北京奥林匹克公园内，是北京为2008年夏季奥运会修建的主游泳馆，也是2008年北京奥运会标志性建筑物之一。");
            scene.setDescribeComplete
                    ("国家游泳中心又称“水立方”(Water Cube)" +
                            "位于北京奥林匹克公园内，是北京为2008年夏季奥运会修建的主游泳馆，也是2008年北京奥运会标志性建筑物之一。\n" +
                            "它的设计方案，是经全球设计竞赛产生的“水的立方”([H2O]3)方案。其与国家体育场(俗称鸟巢)" +
                            "分列于北京城市中轴线北端的两侧，共同形成相对完整的北京历史文化名城形象。\n" +
                            "国家游泳中心规划建设用地62950平方米，总建筑面积65000-80000平方米，其中地下部分的建筑面积不少于15000平方米," +
                            "长宽高分别为 177m ×177m ×30m。\n" +
                            "2008年奥运会期间，国家游泳中心承担游泳、跳水、花样游泳、水球等比赛，可容纳观众坐席17000座，其中永久观众坐席为6000" +
                            "座，奥运会期间增设临时性座位11000个(赛后将拆除)。赛后成为具有国际先进水平的、集游泳、运动、健身、休闲于一体的中心。"
                    );
            scene.save();
            sceneList.add(scene);
        }
        {
            scene = new Scene();
            scene.setName("八大胡同");
            scene.setEnglishName("The eight hutongs");
            scene.setImageId(R.drawable.badahutong);
            scene.setGeography("geo:39.89259,116.3933");
            scene.setDescribeSimple
                    ("八大胡同曾是烟花柳巷的代名词。“八大胡同”在西珠市口大街以北、铁树斜街以南，由西往东依次为：百顺胡同、胭脂胡同、韩家胡同、陕西巷、石头胡同、王广福斜街（现棕树斜街）、朱家胡同、李纱帽胡同（现小力胡同）。");
            scene.setDescribeComplete
                    ("八大胡同曾是烟花柳巷的代名词。“八大胡同”在西珠市口大街以北、铁树斜街以南，由西往东依次为：百顺胡同、胭脂胡同、韩家胡同、陕西巷、石头胡同、王广福斜街（现棕树斜街）、朱家胡同、李纱帽胡同（现小力胡同）。\n" +
                            "北京的胡同多如牛毛，独独八大胡同闻名中外，尤其是在清朝的时候，更是有很多官员常来此地。\n" +
                            "其实，老北京人所说的“八大胡同”，并不专指这八条街巷，而是泛指前门外大栅栏一带，因为在这八条街巷之外的胡同里，还分布着近百家大小妓院。只不过当年，这八条胡同的妓院多是一等二等，妓女的“档次”也比较高，所以才如此知名。"
                    );
            scene.save();
            sceneList.add(scene);
        }
        {
            scene = new Scene();
            scene.setName("潘家园");
            scene.setEnglishName("Panjiayuan Market");
            scene.setImageId(R.drawable.panjiayuan);
            scene.setGeography("geo:39.87546,116.45840999999997");
            scene.setDescribeSimple
                    ("潘家园旧货市场位于北京东三环南路潘家园桥西南，占地4.85" +
                            "万平方米。市场分为地摊区、古建房区、古典家具区、现代收藏区、石雕石刻区、餐饮服务区等六个经营区。");
            scene.setDescribeComplete
                    ("潘家园旧货市场位于北京东三环南路潘家园桥西南，占地4.85" +
                            "万平方米。市场分为地摊区、古建房区、古典家具区、现代收藏区、石雕石刻区、餐饮服务区等六个经营区。主营古旧物品、珠宝玉石、工艺品、收藏品、装饰品，年成交额达数十亿元。市场拥有4000余家经营商户，经商人员近万人，其中百分之六十的经营者来自北京以外的二十八个省、市、自治区，涉及汉、回、满、苗、侗、维、蒙、朝鲜等十几个民族。现在的市场形成于1992年，是伴随着民间古玩艺术品交易的兴起和活跃逐步发展起来的，现在已成为一个古色古香的传播民间文化的大型古玩艺术品市场。"
                    );
            scene.save();
            sceneList.add(scene);
        }
        {
            scene = new Scene();
            scene.setName("国家博物馆");
            scene.setEnglishName("National Museum of China");
            scene.setImageId(R.drawable.guojiabowuguan);
            scene.setGeography("geo:39.90504,116.40168");
            scene.setDescribeSimple
                    ("中国国家博物馆（National Museum of " +
                            "China）简称国博，位于北京市中心天安门广场东侧，东长安街南侧，与人民大会堂东西相对称，是历史与艺术并重，集收藏、展览、研究、考古、公共教育、文化交流于一体的综合性博物馆。隶属于中华人民共和国文化部。");
            scene.setDescribeComplete
                    ("中国国家博物馆（National Museum of " +
                            "China）简称国博，位于北京市中心天安门广场东侧，东长安街南侧，与人民大会堂东西相对称，是历史与艺术并重，集收藏、展览、研究、考古、公共教育、文化交流于一体的综合性博物馆。隶属于中华人民共和国文化部。\n" +
                            "截止至2013年末，中国国家博物馆总建筑面积近20万平方米，国博藏品数量为100余万件，展厅数量48" +
                            "个。是世界上单体建筑面积最大的博物馆，是中华文物收藏量最丰富的博物馆之一，整体规模在世界博物馆中位居前列，2012年游客接待量达到537" +
                            "万人次，2013年达到745万人次，是全世界最受游客欢迎的博物馆之一。\n" +
                            "2017年11月17日，被评为全国未成年人思想道德建设工作先进单位。"
                    );
            scene.save();
            sceneList.add(scene);
        }
        {
            scene = new Scene();
            scene.setName("国家体育馆");
            scene.setEnglishName("National Indoor Stadium");
            scene.setImageId(R.drawable.guojiatiyuguan);
            scene.setGeography("geo:39.99272,116.39660000000002");
            scene.setDescribeSimple
                    ("国家体育馆位于北京奥林匹克公园中心区的南部，总建筑面积为80890" +
                            "平方米，是奥林匹克中心区的标志性建筑之一。国家体育馆俗称折扇，作为北京奥运会三大主场馆之一。");
            scene.setDescribeComplete
                    ("国家体育馆位于北京奥林匹克公园中心区的南部，总建筑面积为80890" +
                            "平方米，是奥林匹克中心区的标志性建筑之一。国家体育馆俗称折扇，作为北京奥运会三大主场馆之一。\n" +
                            "国家体育馆该工程主要由体育馆主体建筑和一个与之紧密相邻的热身馆以及相应的室外环境组成。该场馆内设有固定座位数18000" +
                            "个及临时座位数2000个。在奥运会期间，赛时功能为竞技体操、蹦床、手球的场馆，在残奥会期间的用途则是轮椅篮球的比赛场馆。奥运会后，国家体育馆作为北京市一流体育设施，成为集体体育竞赛、文化娱乐于一体，提供多功能服务给市民活动中心。"
                    );
            scene.save();
            sceneList.add(scene);
        }
        {
            scene = new Scene();
            scene.setName("天安门");
            scene.setEnglishName("Tian’anmen Rostrum");
            scene.setImageId(R.drawable.tiananmen);
            scene.setGeography("geo:39.90885999999998,116.39739");
            scene.setDescribeSimple
                    ("天安门，坐落在中华人民共和国首都北京市的中心、故宫的南端，与天安门广场以及人民英雄纪念碑、毛主席纪念堂、人民大会堂、中国国家博物馆隔长安街相望，占地面积4800平方米，以杰出的建筑艺术和特殊的政治地位为世人所瞩目。");
            scene.setDescribeComplete
                    ("天安门，坐落在中华人民共和国首都北京市的中心、故宫的南端，与天安门广场以及人民英雄纪念碑、毛主席纪念堂、人民大会堂、中国国家博物馆隔长安街相望，占地面积4800平方米，以杰出的建筑艺术和特殊的政治地位为世人所瞩目。\n" +
                            "天安门是明清两代北京皇城的正门，始建于明朝永乐十五年（1417年），最初名“承天门”，寓“承天启运、受命于天”之意。设计者为明代御用建筑匠师蒯祥。清朝顺治八年（1651年）更名为天安门。由城台和城楼两部分组成，有汉白玉石的须弥座，总高34.7米。天安门城楼长66米、宽37米。城台下有券门五阙，中间的券门最大，位于北京皇城中轴线上，过去只有皇帝才可以由此出入。正中门洞上方悬挂着毛泽东画像，两边分别是“中华人民共和国万岁”和“世界人民大团结万岁”的大幅标语。\n" +
                            "民国十四年（1925年）十月十日，故宫博物院成立，天安门开始对民众开放。1949年10月1" +
                            "日，在这里举行了中华人民共和国开国大典，由此被设计入国徽，并成为中华人民共和国的象征。1961" +
                            "年，中华人民共和国国务院公布为第一批全国重点文物保护单位之一。"
                    );
            scene.save();
            sceneList.add(scene);
        }
        {
            scene = new Scene();
            scene.setName("定陵");
            scene.setEnglishName("DingLing");
            scene.setImageId(R.drawable.dingling);
            scene.setGeography("geo:40.2959291683,116.2236391042");
            scene.setDescribeSimple
                    ("明定陵是明代第十三帝神宗显皇帝朱翊钧（年号万历）的陵墓。这里还葬有他的两个皇后（孝端、孝靖）。");
            scene.setDescribeComplete
                    ("明定陵是明代第十三帝神宗显皇帝朱翊钧（年号万历）的陵墓。这里还葬有他的两个皇后（孝端、孝靖）。定陵坐落在大峪山下，位于长陵西南方，建于1584～1590年（万历十二年至万历十八年）。\n" +
                            "定陵的主要建筑有祾恩门、祾恩殿、宝城、明楼和地下宫殿等，占地182000平方米，是明十三陵中一座被发掘了的陵墓。\n" +
                            "在“文化大革命”期间，定陵被当做“牛鬼蛇神”批斗，万历皇帝及两位皇后的尸骨棺椁被毁。且由于当时人们文物保护意识不强和当时特定的历史时期（主要指文化大革命和破四旧），使定陵出土的大量丝织品未得到有效保护，迅速风化，因此，定陵的发掘也被认为是考古史上的一大悲剧。"
                    );
            scene.save();
            sceneList.add(scene);
        }
        {
            scene = new Scene();
            scene.setName("鸟巢");
            scene.setEnglishName("NianChao");
            scene.setImageId(R.drawable.niaochao);
            scene.setGeography("geo:39.9927298104,116.3966130226");
            scene.setDescribeSimple
                    ("国家体育场（鸟巢）位于北京奥林匹克公园中心区南部，为2008年北京奥运会的主体育场。工程总占地面积21公顷，场内观众坐席约为91000个。");
            scene.setDescribeComplete
                    ("国家体育场（鸟巢）位于北京奥林匹克公园中心区南部，为2008年北京奥运会的主体育场。工程总占地面积21公顷，场内观众坐席约为91000个。举行了奥运会、残奥会开闭幕式、田径比赛及足球比赛决赛。奥运会后成为北京市民参与体育活动及享受体育娱乐的大型专业场所，并成为地标性的体育建筑和奥运遗产。\n" +
                            "体育场由雅克·赫尔佐格、德梅隆、艾未未以及李兴刚等设计，由北京城建集团负责施工。体育场的形态如同孕育生命的“巢”和摇篮，寄托着人类对未来的希望。设计者们对这个场馆没有做任何多余的处理，把结构暴露在外，因而自然形成了建筑的外观。\n" +
                            "2003年12月24日开工建设，2008年3月完工，总造价22.67亿元。作为国家标志性建筑，2008年奥运会主体育场，国家体育场结构特点十分显著。体育场为特级体育建筑，大型体育场馆。主体结构设计使用年限100年，耐火等级为一级，抗震设防烈度8度，地下工程防水等级1级。\n" +
                            "2014年4月中国当代十大建筑评审委员会从中国1000多座地标建筑中，综合年代、规模、艺术性和影响力四项指标，初评出二十个建筑。最终由此产生十大当代建筑。北京鸟巢——国家体育场为初评入围建筑之一"
                    );
            scene.save();
            sceneList.add(scene);
        }
    }


}
