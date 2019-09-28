import { Component, OnInit } from '@angular/core';
import {AppService}  from '../app.service';


@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.scss']
})
export class ClientComponent implements OnInit {
  panelOpenState = false;
  data;
  clientData;
  constructor( private appService: AppService) { }



  ngOnInit() {
    // this.appService.getContent(129).subscribe( resgetData =>{
    //   this.data=resgetData
    //   console.log(this.data)
    // })
    this.clientData = this.appService.clientDetail || '<iframe width="1366.0" height="768.0" src="http://35.225.128.32:8080/content/16" style="overflow:hidden;border:none" />';
  }

}
