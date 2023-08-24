package kr.farmstory1.dao;

import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

import kr.farmstory.dto.OrderDTO;
import kr.farmstory1.db.DBHelper;
import kr.farmstory1.db.SQL;

public class OrderDAO extends DBHelper{
	
	// 기본 CRUD
	public void insertOrder(OrderDTO dto) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_ORDER);
			psmt.setInt(1, dto.getOrderProduct());
			psmt.setInt(2, dto.getOrderCount());
			psmt.setInt(3, dto.getOrderDelivery());
			psmt.setInt(4, dto.getOrderPrice());
			psmt.setInt(5, dto.getOrderTotal());
			psmt.setString(6, dto.getReceiver());
			psmt.setString(7, dto.getHp());
			psmt.setString(8, dto.getZip());
			psmt.setString(9, dto.getAddr1());
			psmt.setString(10, dto.getAddr2());
			psmt.setString(11, dto.getOrderEtc());
			psmt.setString(12, dto.getOrderUser());
			
			psmt.executeUpdate();
			
			close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public OrderDTO selectOrder(int orderNo) {
		
		return null;
	}
	
	public List<OrderDTO> selectOrders() {
		List<OrderDTO> orders = new ArrayList<>();
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SETECT_ORDERS);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				OrderDTO dto = new OrderDTO();
				dto.setOrderNo(rs.getInt(1));
				dto.setOrderProduct(rs.getInt(2));
				dto.setOrderCount(rs.getInt(3));
				dto.setOrderDelivery(rs.getInt(4));
				dto.setOrderPrice(rs.getInt(5));
				dto.setOrderTotal(rs.getInt(6));
				dto.setReceiver(rs.getString(7));
				dto.setHp(rs.getString(8));
				dto.setZip(rs.getString(9));
				dto.setAddr1(rs.getString(10));
				dto.setAddr2(rs.getString(11));
				dto.setOrderEtc(rs.getString(12));
				dto.setOrderUser(rs.getString(13));
				dto.setOrderDate(rs.getString(14));
				dto.setpName(rs.getString(15));
				dto.setThumb1(rs.getString(16));
				
				orders.add(dto);
			}
			close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return orders;
	}
	
	public void updateOrder(OrderDTO dto) {}
	
	public void deleteOrder(String orderNo) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.DELETE_ORDER);
			psmt.setString(1, orderNo);
			psmt.executeUpdate();
			
			close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// 추가
	public int selectCountTotal() { // 전체 게시물 갯수 조회
			
		int total = 0;
		
		try {
		conn = getConnection();
		psmt = conn.prepareStatement(SQL.SELECT_ORDER_COUNT_TOTAL);
		rs = psmt.executeQuery();
		
		if(rs.next()) {
			total = rs.getInt(1);
		}
		close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return total;
	}
}
