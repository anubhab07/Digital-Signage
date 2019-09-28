import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import{DialogData} from './/matcomponent/matcomponent.component';
// import 'rxjs/add/operator/map';


@Injectable({
  providedIn: 'root'
})
export class AppService {
  details;
  constructor(private _http: HttpClient) { }
  addProductInAPI(data:DialogData) {
    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    //let options = new RequestOptions({ method: RequestMethod.Post, headers: headers });
    let body = JSON.stringify(data);
    console.log('calling the url');
    return this._http.post('http://localhost:4200/api/product/InsertProduct', body,{})
      // .map((res: Response) => res.json());
  }

  getContent(deviceId){
    let url = ' http://localhost:4200/getContent' + deviceId ;
    return this._http.get(url);
  }

  getDashboardData() {
    return this._http.get('assets/data/fetchAllDevices.json');
  }

  saveNewLayout(data) {
    return this._http.post('http://35.225.128.32:8080/saveContent', data, {});
  }
}
