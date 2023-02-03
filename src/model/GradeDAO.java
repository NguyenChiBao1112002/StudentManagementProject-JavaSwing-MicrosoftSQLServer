/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import database.DatabaseUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.AbstractList;
import java.util.List;


/**
 *
 * @author FPT SHOP
 */
public class GradeDAO {
    public static List<Grade> ls = new ArrayList<>();
    
    
//    public int add(Grade d){
//        ls.add(d);
//        return 1;
//    }
    
    public int add(Grade g) {
        Connection conn = null;
        PreparedStatement sttm = null;
        try {
            String sSQL = "insert into Grade(MaSV,AnhVan,TinHoc,GDTC) values(?,?,?,?)";
            conn = DatabaseUtils.getDBConnect();
            sttm = conn.prepareStatement(sSQL);
            sttm.setString(1, g.getSv().getMaSV());
            sttm.setDouble(2, g.getAnhVan());
            sttm.setDouble(3, g.getTinHoc());
            sttm.setDouble(4, g.getGdtc());


            if (sttm.executeUpdate() > 0) {
//                System.out.println("Thêm thành công");
                return 1;
            }
        } catch (Exception e) {
//            System.out.println("Error: " + e.toString());

        } finally {
            try {
                conn.close();
                sttm.close();
            } catch (Exception e) {

            }
        }
        return -1; //neu them khong thanh cong
    }
    
//    public List<Grade> getAllGrade(){
//        return ls;
//    }
    
    public List<Grade> getAllGrade() {
        List<Grade> ls = new ArrayList<>();
        Connection conn = null;
        Statement sttm = null;
        ResultSet rs = null;
        try {
            String sSQL = "SELECT dbo.Grade.MaSV, dbo.SinhVien.TenSV, "
                    + "dbo.Grade.AnhVan, dbo.Grade.TinHoc, dbo.Grade.GDTC "
                    + "FROM   dbo.Grade "
                    + "INNER JOIN dbo.SinhVien ON dbo.Grade.MaSV = dbo.SinhVien.MaSV";
            conn = DatabaseUtils.getDBConnect();
            sttm = conn.createStatement();
            rs = sttm.executeQuery(sSQL);
            while (rs.next()) {
                Grade g = new Grade();
                g.setSv(new SinhVien(rs.getString(1),rs.getString(2)));
                g.setAnhVan(rs.getDouble(3));
                g.setTinHoc(rs.getDouble(4));
                g.setGdtc(rs.getDouble(5));
                ls.add(g);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        } finally {
            try {
                rs.close();
                sttm.close();
                conn.close();
            } catch (Exception e) {
            }
        }
        return ls;
    }
    
    public Grade getOneGradeByMaSV(String maSV) {
        Connection conn = null;
        PreparedStatement sttm = null;
        ResultSet rs = null;


        try {
            String sSQL = "SELECT dbo.Grade.MaSV, dbo.SinhVien.TenSV, "
                    + "dbo.Grade.AnhVan, dbo.Grade.TinHoc, dbo.Grade.GDTC "
                    + "FROM   dbo.Grade INNER JOIN dbo.SinhVien "
                    + "ON dbo.Grade.MaSV = dbo.SinhVien.MaSV where Grade.MaSV = ?";
            conn = DatabaseUtils.getDBConnect();
            sttm = conn.prepareStatement(sSQL);
            sttm.setString(1, maSV);
            rs = sttm.executeQuery();
            while (rs.next()) {
                Grade g = new Grade();
                g.setSv(new SinhVien(rs.getString(1),rs.getString(2)));
                g.setAnhVan(rs.getDouble(3));
                g.setTinHoc(rs.getDouble(4));
                g.setGdtc(rs.getDouble(5));
                return g;
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        } finally {
            try {
                conn.close();
                rs.close();
                sttm.close();
            } catch (Exception e) {
            }
        }
        return null;
    }
    
//    public Grade getOneGradeByMaSV(String masv){
//        for (Grade d : ls) {
//            if(d.getSv().getMaSV().equalsIgnoreCase(masv)){
//                return d;
//            }
//        }
//        return null;
//    }
    
//    public int updateGrade(Grade dNew){
//        for (Grade d : ls) {
//            if(d.getSv().getMaSV().equalsIgnoreCase(dNew.getSv().getMaSV())){
//                d.setAnhVan(dNew.getAnhVan());
//                d.setGdtc(dNew.getGdtc());
//                d.setTinHoc(dNew.getTinHoc());
//                return 1;
//            }
//        }
//        return -1;
//    }
    
    public int updateGrade(Grade g) {
        Connection conn = null;
        PreparedStatement sttm = null;
        try {
            String sSQL = "update Grade set AnhVan = ?, TinHoc = ?, GDTC= ? where MaSV = ?";
            conn = DatabaseUtils.getDBConnect();
            sttm = conn.prepareStatement(sSQL);
            sttm.setString(4, g.getSv().getMaSV());
            sttm.setDouble(1, g.getAnhVan());
            sttm.setDouble(2, g.getTinHoc());
            sttm.setDouble(3, g.getGdtc());


            if (sttm.executeUpdate() > 0) {
//                System.out.println("Cập nhật thành công");
                return 1;
            }
        } catch (Exception e) {
//            System.out.println("Error: " + e.toString());

        } finally {
            try {
                conn.close();
                sttm.close();
            } catch (Exception e) {

            }
        }
        return -1; //neu them khong thanh cong
    }
    
    
    public int delGrade(String maSV) {
        Connection conn = null;
        PreparedStatement sttm = null;
        try {
            String sSQL = "delete Grade where MaSV = ?";
            conn = DatabaseUtils.getDBConnect();
            sttm = conn.prepareStatement(sSQL);
            sttm.setString(1, maSV);



            if (sttm.executeUpdate() > 0) {
//                System.out.println("Xoá thành công");
                return 1;
            }
        } catch (Exception e) {
//            System.out.println("Error: " + e.toString());

        } finally {
            try {
                conn.close();
                sttm.close();
            } catch (Exception e) {

            }
        }
        return -1; //neu them khong thanh cong
    }
    
    
//    public int delGrade(String masv){
//        Grade d = getOneGradeByMaSV(masv);
//        if(d!=null){
//            ls.remove(d);
//            return 1;
//        }
//        return -1;
//    }
    
    
    
    public Grade getAtPosition(int pos){
        return ls.get(pos);
    }
        
}
