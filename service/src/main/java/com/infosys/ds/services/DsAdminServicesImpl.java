package com.infosys.ds.services;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.infosys.ds.model.City;
import com.infosys.ds.model.Device;
import com.infosys.ds.model.DeviceProfile;
import com.infosys.ds.model.Location;
import com.infosys.ds.response.CityDataResponse;
import com.infosys.ds.response.DeviceDataResponse;
import com.infosys.ds.response.ProductRequest;

@Service
@CrossOrigin("*")
public class DsAdminServicesImpl implements DsAdminServices {

	@Autowired
	JdbcTemplate jdbcTemplate;

	/*
	 * @Override public CityDataResponse getLoadingData() { CityDataResponse
	 * loadDataResponse = new CityDataResponse(); HashSet<String> countrys = new
	 * HashSet<String>(); HashSet<String> states = new HashSet<String>();
	 * HashSet<String> citys = new HashSet<String>(); HashSet<String> resolutions =
	 * new HashSet<String>(); List<City> listCitys =
	 * jdbcTemplate.query("Select * from digi_sign.city", (ResultSet rs, int rowNum)
	 * -> new City(rs.getInt("city_id") ,rs.getString("country")
	 * ,rs.getString("state") ,rs.getString("city")
	 * 
	 * ) ); for (City city : listCitys) { countrys.add(city.getCountry());
	 * states.add(city.getState ()); citys.add(city.getCity());
	 * 
	 * } loadDataResponse.setCitys(citys); loadDataResponse.setCountrys(countrys);
	 * loadDataResponse.setStates(states);
	 * 
	 * 
	 * 
	 * return loadDataResponse; }
	 */
	@Override
	public DeviceProfile getProfileForRes(DeviceProfile deviceProfile)
	{
		Integer queryForObject = jdbcTemplate.queryForObject("Select count(*) from digi_sign.device_profile dd where dd.height="+deviceProfile.getHeight()+" and dd.width="+deviceProfile.getWidth(),Integer.class);
		 if(queryForObject==0)
	 {
		int update = jdbcTemplate.update("insert into digi_sign.device_profile (profile_desc,height,width) values (?,?,?)" ,
				new Object[] {deviceProfile.getProfileDesc(),deviceProfile.getHeight(),deviceProfile.getWidth()});
		
			
	 }
	 deviceProfile = jdbcTemplate.queryForObject("Select * from digi_sign.device_profile dd where dd.height="+deviceProfile.getHeight()+" and dd.width="+deviceProfile.getWidth(),
				new BeanPropertyRowMapper<DeviceProfile>(DeviceProfile.class));
	 
	 return deviceProfile;
	}
	@Override
	public City getCity(City city) {
		

		Integer queryForObject = jdbcTemplate.queryForObject("Select count(*) from digi_sign.city dd where dd.country='"+city.getCountry()+"' and dd.state='"+city.getState()+"' and dd.city='"+city.getCity()+"'",Integer.class);
		 if(queryForObject==0)
	 {
		int update = jdbcTemplate.update("insert into digi_sign.city  (country,state,city) values (?,?,?)" ,
				new Object[] {city.getCountry(),city.getState(),city.getCity()});
		
			
	 }
		 city = jdbcTemplate.queryForObject("Select * from digi_sign.city dd "+"where dd.country='"+city.getCountry()+"' and dd.state='"+city.getState()+"' and dd.city='"+city.getCity()+"'" ,
				new BeanPropertyRowMapper<City>(City.class));
	return city;
	}
	@Override
	public Location getLocation(Location location) {

		 String filter=" where dd.city_id="+location.getCityId()+" and dd.area='"+location.getArea()+"' and dd.locality='"+location.getLocality()+"'";
		Integer queryForObject = jdbcTemplate.queryForObject("Select count(*) from digi_sign.location dd"+filter,Integer.class);
		 if(queryForObject==0)
	 {
		int update = jdbcTemplate.update("insert into digi_sign.location  (city_id,area,locality) values (?,?,?)" ,
				new Object[] {location.getCityId(),location.getArea(),location.getLocality()});
		
			
	 }
		 location = jdbcTemplate.queryForObject("Select * from digi_sign.location dd "+filter,
				new BeanPropertyRowMapper<Location>(Location.class));
	return location;
	}
	
	@Override
	public BigInteger createDevice(ProductRequest productRequest) {
		
		
		String res = productRequest.getRes();
		String[] split = res.split("\\*");
		DeviceProfile deviceProfile = new DeviceProfile(res,Integer.parseInt(split[0]),Integer.parseInt(split[1]));
		Integer profileId = getProfileForRes(deviceProfile).getProfileId();
		
		 City city = new City( productRequest.getCountry()	, productRequest.getState(), productRequest.getCity());
		 city= getCity(city);
		 
		 Location location = new Location(city.getCityId(), productRequest.getArea(), productRequest.getLocality());
		Integer locationId = getLocation(location).getLocationId();
		 
		 String sql="insert into digi_sign.devices (device_desc,device_type,loc_id,profile_id,is_active) value (?,?,?,?,?)";
		 KeyHolder keyHolder = new GeneratedKeyHolder();
		 
		    jdbcTemplate.update(connection -> {
		        PreparedStatement ps = connection
		          .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		          ps.setString(1, productRequest.getDesc());
		          ps.setString(2, productRequest.getDeviceType());
		          ps.setInt(3, locationId);
		          ps.setInt(4, profileId);
		          ps.setInt(5, 1);
		          
		          return ps;
		        }, keyHolder);
		 
		    BigInteger key = (BigInteger) keyHolder.getKey();
		    
		 
		return key;
	}
	@Override
	public DeviceDataResponse getDeviceInfo(Integer device_id) {
	String sql="select device_id,device_desc,device_type,de.loc_id as loc_id,deloc.city_id as city_id,country,state,city,area,locality,de.profile_id as profile_id,profile_desc,height,width, is_Active from digi_sign.devices de,digi_sign.device_profile depro,digi_sign.location deloc,digi_sign.city decity\r\n" + 
			"where de.device_id=" +device_id+ 
			" and de.profile_id=depro.profile_id\r\n" + 
			"and deloc.location_id=de.loc_id\r\n" + 
			"and decity.city_id=deloc.city_id";
	
	List<DeviceDataResponse> queryForObject = jdbcTemplate.query(sql, new BeanPropertyRowMapper(DeviceDataResponse.class));
	if(queryForObject.isEmpty())
		return new DeviceDataResponse();
		
		
		return queryForObject.get(0);
	}
	@Override
	public List<DeviceDataResponse> getAllDevicesInfo() {
	String sql="select device_id,device_desc,device_type,de.loc_id as loc_id,deloc.city_id as city_id,country,state,city,area,locality,de.profile_id as profile_id,profile_desc,height,width, is_Active from digi_sign.devices de,digi_sign.device_profile depro,digi_sign.location deloc,digi_sign.city decity\r\n" + 
			"where "+
			" de.profile_id=depro.profile_id\r\n" + 
			"and deloc.location_id=de.loc_id\r\n" + 
			"and decity.city_id=deloc.city_id";
	
	List<DeviceDataResponse> queryForObject = jdbcTemplate.query(sql, new BeanPropertyRowMapper(DeviceDataResponse.class));
	if(queryForObject.isEmpty()) {
		ArrayList<DeviceDataResponse> arrayList = new ArrayList<DeviceDataResponse>();
		return arrayList;
	}
		
		return queryForObject;
	}

}
