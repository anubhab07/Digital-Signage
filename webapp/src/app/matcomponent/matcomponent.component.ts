import { Component, OnInit,Inject } from '@angular/core';
import {MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import {AdminComponent} from '../admin/admin.component';
import { Injectable } from '@angular/core';
import {AppService} from '../app.service';
import { Router } from '@angular/router';

export interface DialogData {
  res:string;
  Country: string;
  State:string;
  City:String;
  Area:string;
  Locality:string;
  Desc:String;

}
export interface Resolution {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-matcomponent',
  templateUrl: './matcomponent.component.html',
  styleUrls: ['./matcomponent.component.scss']
})


export class MatcomponentComponent implements OnInit {
  res: Resolution[] = [
    {value: '0', viewValue: '600*800'},
    {value: '1', viewValue: '600*1066'},
    {value: '2', viewValue: '600*1200'},
    {value: '0', viewValue: '720*960'},
    {value: '1', viewValue: '720*1280'},
    {value: '2', viewValue: '720*1440'},
    {value: '0', viewValue: '1080*1440'},
    {value: '1', viewValue: '1080*1920'},
    {value: '2', viewValue: '1080*2160'},
    {value: '0', viewValue: '1920*2560'},
    {value: '1', viewValue: '1920*3414'},
    {value: '2', viewValue: '1920*3840'}
  ];
  //this.res={{r.viewValue}}
  constructor(
    private appService: AppService,
    public dialogRef: MatDialogRef<AdminComponent>,
    private router: Router,
    @Inject(MAT_DIALOG_DATA) public data: DialogData,
    ) {
    }




  ngOnInit() {
    this.data.res="720*1280";
  }
  onNoClick(): void {
    this.dialogRef.close();
  }
  onOkClick(){
    console.log(this.data);
    this.appService.addProductInAPI(this.data);
    this.appService.details = this.data;
    this.router.navigateByUrl('layout/1');
  }

}
