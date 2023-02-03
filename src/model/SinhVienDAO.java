/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import database.DatabaseUtils;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.text.SimpleDateFormat;

/**
 *
 * @author FPT SHOP
 */
public class SinhVienDAO {

    SimpleDateFormat format_date = new SimpleDateFormat("yyyy/MM/dd");

    public static List<SinhVien> ls = new ArrayList<>();

//    public int add(SinhVien sv){
//        ls.add(sv);
//        return 1;
//    }
    public int add(SinhVien sv) {
        Connection conn = null;
        PreparedStatement sttm = null;
        try {
            String sSQL = "insert into SinhVien(MaSV,TenSV,NgaySinh,GioiTinh,DiaChi,HinhAnh) values(?,?,?,?,?,?)";
            conn = DatabaseUtils.getDBConnect();
            sttm = conn.prepareStatement(sSQL);
            sttm.setString(1, sv.getMaSV());
            sttm.setString(2, sv.getTenSV());
            sttm.setString(3, format_date.format(sv.getNgaySinh()));//ngay sinh
            sttm.setBoolean(4, sv.isGioiTinh());
            sttm.setString(5, sv.getDiaChi());
            sttm.setString(6, sv.getHinhAnh());

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

    public int updateSinhVienByID(SinhVien sv) {
        Connection conn = null;
        PreparedStatement sttm = null;
        try {
            String sSQL = "update SinhVien set TenSV=?,NgaySinh=?,GioiTinh=?,DiaChi=?,HinhAnh=? where MaSV=?";
            conn = DatabaseUtils.getDBConnect();
            sttm = conn.prepareStatement(sSQL);
            sttm.setString(6, sv.getMaSV());
            sttm.setString(1, sv.getTenSV());
            sttm.setString(2, format_date.format(sv.getNgaySinh()));//ngay sinh
            sttm.setBoolean(3, sv.isGioiTinh());
            sttm.setString(4, sv.getDiaChi());
            sttm.setString(5, sv.getHinhAnh());

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

//    public List<SinhVien> getAllSinhVien() {
//        return ls;
//    }
    public List<SinhVien> getAllSinhVien() {
        List<SinhVien> ls = new ArrayList<>();
        Connection conn = null;
        Statement sttm = null;
        ResultSet rs = null;
        try {
            String sSQL = "select * from SinhVien";
            conn = DatabaseUtils.getDBConnect();
            sttm = conn.createStatement();
            rs = sttm.executeQuery(sSQL);
            while (rs.next()) {
                SinhVien sv = new SinhVien();
                sv.setMaSV(rs.getString(1));
                sv.setTenSV(rs.getString(2));
                sv.setNgaySinh(rs.getDate(3));
                sv.setGioiTinh(rs.getBoolean(4));
                sv.setDiaChi(rs.getString(5));
                sv.setHinhAnh(rs.getString(6));
                ls.add(sv);
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

    public int delSinhVienByID(String maSV) {
        Connection conn = null;
        PreparedStatement sttm = null;
        try {
            String sSQL = "delete SinhVien where MaSV=?";
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

//    public int delSinhVienByID(String ma) {
//        for (SinhVien sv : ls) {
//            if (sv.getMaSV().equalsIgnoreCase(ma)) {// ss 2 chuoi, ko phan biet hoa thuong
//                ls.remove(sv);
//                return 1;
//            }
//        }
//        return -1;
//    }
//    public SinhVien getSinhVienByID(String id) {
//        for (SinhVien sv : ls) {
//            if (sv.getMaSV().equalsIgnoreCase(id)) {
//                return sv;
//            }
//        }
//        return null;
//    }
    public SinhVien getSinhVienByID(String maSV) {
        Connection conn = null;
        PreparedStatement sttm = null;
        ResultSet rs = null;
        SinhVien sv = new SinhVien();

        try {
            String sSQL = "select * from SinhVien where MaSV=?";
            conn = DatabaseUtils.getDBConnect();
            sttm = conn.prepareStatement(sSQL);
            sttm.setString(1, maSV);
            rs = sttm.executeQuery();
            while (rs.next()) {
                sv.setMaSV(rs.getString(1));
                sv.setTenSV(rs.getString(2));
                sv.setNgaySinh(rs.getDate(3));
                sv.setGioiTinh(rs.getBoolean(4));
                sv.setDiaChi(rs.getString(5));
                sv.setHinhAnh(rs.getString(6));
                return sv;
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

//    public int updateSinhVienByID(SinhVien svNew){
//        for (SinhVien sv : ls) {
//            if(sv.getMaSV().equalsIgnoreCase(svNew.getMaSV())){
//                sv.setDiaChi(svNew.getDiaChi());
//                sv.setGioiTinh(svNew.isGioiTinh());
//                sv.setHinhAnh(svNew.getHinhAnh());
//                sv.setNgaySinh(svNew.getNgaySinh());
//                sv.setTenSV(svNew.getTenSV());
//                return 1;
//            }
//        }
//        return -1;
//    }
}
