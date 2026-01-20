package tp05.templateMethod2.utilitaires.template;

import java.sql.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Un ensemble de méthodes pratiques pour exécuter des requêtes SQL.
 */
public class SQLSimpleHelper {
    /**
     * Helper class statique : pas d'instanciation.
     */
    private SQLSimpleHelper() {
    }

    /**
     * Insère une entrée dans la base, et récupère son id (forcément un long).
     * 
     * @param sql  : la requête d'insertion, avec des '?' à la place des parties
     *             variables.
     * @param args les valeurs des '?'
     * @throws SQLTemplateException en cas de problème.
     */
    public static void insert(Connection connection, String sql, Object... args) {
        List<List<Object>> argList = Collections.singletonList(Arrays.asList(args));
        new InsertTemplateAux(connection, sql, argList).execute();
    }

    /**
     * Exécute une modification sur une entrée.
     * 
     * @param sql  une requête de modification ; des '?' signalent des arguments.
     * @param args les valeurs des arguments.
     */
    public static void update(Connection connection, String sql, Object... args) {
        List<List<Object>> argList = Collections.singletonList(Arrays.asList(args));
        updateBatch(connection, sql, argList);
    }

    /**
     * Exécute une modification sur une série d'entrée
     * 
     * @param sql     une requête de modification ; des '?' signalent des arguments.
     * @param argList une liste de t-uple, chacun des t-uples correspondant à une
     *                série d'arguments.
     */
    public static void updateBatch(Connection connection, String sql, List<List<Object>> argList) {
        new SQLUpdateTemplateAux(connection, sql, argList).execute();
    }

    /**
     * Exécute une requête qui doit retourner exactement un entier (ou null).
     * 
     * @param sql
     * @param args
     * @return un Integer ou null, selon que la requête a un retour ou non.
     * @throws SQLTemplateException si la requête retourne plus d'un résultat.
     */
    public static Integer sqlIntQuery(Connection connection, String sql, Object... args) {
        List<Integer> res = new SQLIntegerQueryAux(connection, sql).execute(args);
        if (res.isEmpty())
            return null;
        else if (res.size() == 1)
            return res.get(0);
        else
            throw new SQLTemplateException("la requête renvoie " + res.size() + " entiers " + res);
    }

    public static Long sqlLongQuery(Connection connection, String sql, Object... args) {
        List<Long> res = new SQLLongQueryAux(connection, sql).execute(args);
        if (res.isEmpty())
            return null;
        else if (res.size() == 1)
            return res.get(0);
        else
            throw new SQLTemplateException("la requête renvoie " + res.size() + " entiers " + res);
    }

    /**
     * Template pour l'insertion (id fixé).
     */
    private static class InsertTemplateAux extends SQLStatementTemplate<Void> {

        public InsertTemplateAux(Connection connection, String sql, List<List<Object>> argList) {
            super(connection, sql, argList);
        }

        @Override
        protected Void initResult() {
            return null; 
        }

        @Override
        protected void performStatement(Void currentResult, PreparedStatement pst) throws SQLException {
            pst.executeUpdate();
        }
    }

    private static class SQLUpdateTemplateAux extends SQLStatementTemplate<Void> {

        public SQLUpdateTemplateAux(Connection connection, String sql, List<List<Object>> argList) {
            super(connection, sql, argList);
        }

        @Override
        protected Void initResult() {
            return null;
        }

        @Override
        protected void performStatement(Void currentResult, PreparedStatement pst) throws SQLException {
            pst.executeUpdate();
        }
    }

    private static class SQLIntegerQueryAux extends SQLQueryTemplate<Integer> {
        public SQLIntegerQueryAux(Connection connection, String sql) {
            super(connection, sql);
        }

        @Override
        protected Integer extractData(ResultSet res) throws SQLException {
            return res.getInt(1);
        }
    }

    private static class SQLLongQueryAux extends SQLQueryTemplate<Long> {
        public SQLLongQueryAux(Connection connection, String sql) {
            super(connection, sql);
        }

        @Override
        protected Long extractData(ResultSet res) throws SQLException {
            return res.getLong(1);
        }
    }
}