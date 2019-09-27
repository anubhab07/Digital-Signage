import { Component, OnInit, ViewContainerRef, ViewChild, TemplateRef } from '@angular/core';
import { FileHandle } from '../drag-drop.directive';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss']
})
export class LayoutComponent implements OnInit {
  @ViewChild('innerChild', {static: false}) splitTemplate;
  layoutLstVert = [];
  counterVert = 1;
  counterHorz = 2;
  file;
  files: FileHandle[] = [];

  constructor(private viewContainerRef: ViewContainerRef) { }

  ngOnInit() {
    this.layoutLstVert.push({idVert: this.counterVert, childArr: [{idHorz: this.counterHorz, type: 'pptx', file: ''}]});
    this.increaseBothCounter();
    // this.layoutLstVert.push({idVert: 2, childArr: [{idHorz: 2}]});
  }



  filesDropped(files, layout, child,inner): void {
    console.log(files);
    console.log(this.splitTemplate.nativeElement.offsetWidth);
    console.log(this.splitTemplate.nativeElement.offsetHeight);
    console.log(this.splitTemplate.nativeElement.offsetLeft);
    console.log(this.splitTemplate.nativeElement.getBoundingClientRect());
    this.layoutLstVert.forEach(lay => {
      if (lay.idVert === layout.idVert) {
        lay.childArr.forEach(chld => {
          if (chld.idHorz === child.idHorz) {
            chld.file = files[0].file;
            child.url = files[0].url;
          }
        });
        // lay.childArr.file =
        // lay.childArr.url
      }
    });
    console.log(this.layoutLstVert)
  }

  splitVertical(tempRef) {
    console.log(tempRef);
    this.layoutLstVert.push({idVert: this.counterVert, childArr: [{idHorz: this.counterHorz, type: 'pptx', file: ''}]});
    this.increaseBothCounter();
    // const factory = tempRef.elRef.nativeElement.createEmbeddedView(this.splitTemplate);
    // tempRef.elRef.innerHTML = this.splitTemplate.elementRef.nativeElement;
  }

  splitHorizontal(objHorz) {
    console.log(objHorz);
    this.layoutLstVert.forEach(lay => {
      if (lay.idVert === objHorz.idVert) {
        lay.childArr.push({idHorz: this.counterHorz});
        this.increaseHorz();
      }
    });
    console.log(this.layoutLstVert);
  }

  onFileChanged(event) {
    const file = event.target.files[0];
    console.log(file);
  }

  increaseVert() {
    this.counterVert++;
  }
  increaseHorz() {
    this.counterHorz++;
  }
  increaseBothCounter() {
    this.counterVert++;
    this.counterHorz++;
  }



}
