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
  constructor( private appService:AppService) { }



  ngOnInit() {
    this.appService.getContent(129).subscribe( resgetData =>{
      this.data=resgetData
      console.log(this.data)
    })
  }

}
