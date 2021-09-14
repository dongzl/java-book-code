package calcite;

import org.apache.calcite.config.CalciteConnectionConfigImpl;
import org.apache.calcite.jdbc.CalciteSchema;
import org.apache.calcite.prepare.CalciteCatalogReader;
import org.apache.calcite.rel.type.RelDataType;
import org.apache.calcite.rel.type.RelDataTypeFactory;
import org.apache.calcite.rel.type.RelDataTypeSystem;
import org.apache.calcite.rel.type.RelDataTypeSystemImpl;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.calcite.schema.impl.AbstractTable;
import org.apache.calcite.sql.SqlDialect;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.SqlSelect;
import org.apache.calcite.sql.fun.SqlStdOperatorTable;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.sql.type.BasicSqlType;
import org.apache.calcite.sql.type.SqlTypeFactoryImpl;
import org.apache.calcite.sql.type.SqlTypeName;
import org.apache.calcite.sql.validate.SqlValidator;
import org.apache.calcite.sql.validate.SqlValidatorUtil;
import org.apache.calcite.tools.Frameworks;

import java.util.Properties;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2021/2/18 3:06 下午
 */
public class CalciteTest2 {

    public static void main(String[] args) throws Exception {

        String sql = "select u.id as user_id, u.name as user_name, j.company as user_company, u.age as user_age \n" +
                "from users u join jobs j on u.name=j.name\n" +
                "where u.age > 30 and j.id>10\n" +
                "order by user_id";

        SchemaPlus rootSchema = Frameworks.createRootSchema(true);
        rootSchema.add("USERS", new AbstractTable() {
            @Override
            public RelDataType getRowType(RelDataTypeFactory typeFactory) {
                RelDataTypeFactory.Builder builder = typeFactory.builder();
                builder.add("ID", new BasicSqlType(new RelDataTypeSystemImpl() {}, SqlTypeName.INTEGER));
                builder.add("NAME", new BasicSqlType(new RelDataTypeSystemImpl() {}, SqlTypeName.CHAR));
                builder.add("AGE", new BasicSqlType(new RelDataTypeSystemImpl() {}, SqlTypeName.INTEGER));
                return builder.build();
            }
        });

        rootSchema.add("JOBS", new AbstractTable() {
            @Override
            public RelDataType getRowType(final RelDataTypeFactory typeFactory) {
                RelDataTypeFactory.Builder builder = typeFactory.builder();

                builder.add("ID", new BasicSqlType(new RelDataTypeSystemImpl() {}, SqlTypeName.INTEGER));
                builder.add("NAME", new BasicSqlType(new RelDataTypeSystemImpl() {}, SqlTypeName.CHAR));
                builder.add("COMPANY", new BasicSqlType(new RelDataTypeSystemImpl() {}, SqlTypeName.CHAR));
                return builder.build();
            }
        });

        SqlParser parser = SqlParser.create("update table1 set column_a = 1, column_b = column_b + 1 where column_c = 2 or column_d between 1 and 2 and column_e > 100", SqlParser.Config.DEFAULT);
        SqlNode sqlNode = parser.parseStmt();
        //((SqlSelect) sqlNode).getSelectList();
        System.out.println(sqlNode);

//        SqlTypeFactoryImpl factory = new SqlTypeFactoryImpl(RelDataTypeSystem.DEFAULT);
//        CalciteCatalogReader calciteCatalogReader = new CalciteCatalogReader(
//                CalciteSchema.from(rootSchema),
//                CalciteSchema.from(rootSchema).path(null),
//                factory,
//                new CalciteConnectionConfigImpl(new Properties()));
//
//        //note: 校验（包括对表名，字段名，函数名，字段类型的校验。）
//        SqlValidator validator = SqlValidatorUtil.newValidator(SqlStdOperatorTable.instance(), calciteCatalogReader, factory,
//                conformance(frameworkConfig));
//        SqlNode validateSqlNode = validator.validate(sqlNode);
    }
}
