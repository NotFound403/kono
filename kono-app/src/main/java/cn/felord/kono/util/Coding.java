package cn.felord.kono.util;

/**
 * 代码生成器.
 *
 * @author felord.cn
 * @since 17:04
 */
public class Coding {
    public static void main(String[] args) {
        //          maven 工程 main 包的全路径
        final String mainDir = "C:\\Users\\xfa00\\IdeaProjects\\kono\\kono-app\\src\\main\\";

        new CodeGenerator.ConfigBuilder()
                // 数据库连接
                .dbUrl("jdbc:mysql://localhost:3306/kono")
                // 账户
                .userName("root")
                // 密码
                .password("123456")
                // 生成类位置
                .dir(mainDir + "java")
                // 生成xml 位置
                .xmlDir(mainDir + "resources")
                // 包引用路径
                .packageName("cn.felord.kono")
                .build()
                //
                .code("user_info");

    }
}
