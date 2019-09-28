import { Component, OnInit, ViewContainerRef, ViewChild, TemplateRef } from '@angular/core';
import { FileHandle } from '../drag-drop.directive';
import { AppService } from '../app.service';
interface IChild {
  idHorz: number;
  type: string;
  file: any;
  domId: string;
  startX: number;
  startY: number;
  width: number;
  url: string;
  height: number;
  base64Url: string;
}
@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss']
})
export class LayoutComponent implements OnInit {
  @ViewChild('innerChild', { static: false }) splitTemplate;
  layoutLstVert = [];
  counterVert = 1;
  counterHorz = 2;
  file;
  files: FileHandle[] = [];

  constructor(private viewContainerRef: ViewContainerRef, private appService: AppService) { }

  ngOnInit() {
    this.layoutLstVert.push({ idVert: this.counterVert, childArr: [{ idHorz: this.counterHorz, type: 'pptx', file: '' }] });
    this.increaseBothCounter();
    // this.layoutLstVert.push({idVert: 2, childArr: [{idHorz: 2}]});
  }



  filesDropped(files, layout, child): void {

    this.layoutLstVert.forEach(lay => {
      if (lay.idVert === layout.idVert) {
        lay.childArr.forEach((chld: IChild) => {
          if (chld.idHorz === child.idHorz) {
            chld.file = files[0].file;
            chld.url = files[0].url;
            chld.type = chld.file.type;
            this.getBase64(chld.file).then((a: string) => {
              chld.base64Url = a;
            });
          }
        });
        // lay.childArr.file =
        // lay.childArr.url
      }
    });
    // console.log(this.layoutLstVert);

  }
  getBase64(file) {
    return new Promise((resolve, reject) => {
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = () => {
        let encoded = reader.result.toString().replace(/^data:(.*,)?/, '');
        if ((encoded.length % 4) > 0) {
          encoded += '='.repeat(4 - (encoded.length % 4));
        }
        resolve(encoded);
      };
      reader.onerror = error => reject(error);
    });
    // const reader = new FileReader();
    // reader.readAsDataURL(file);
    // reader.onload = function () {
    //   console.log(reader.result);
    // };
    // reader.onerror = function (error) {
    //   console.log('Error: ', error);
    // };
  }

  splitVertical(tempRef) {
    console.log(tempRef);
    this.layoutLstVert.push({ idVert: this.counterVert, childArr: [{ idHorz: this.counterHorz, type: 'pptx', file: '' }] });
    this.increaseBothCounter();
    // const factory = tempRef.elRef.nativeElement.createEmbeddedView(this.splitTemplate);
    // tempRef.elRef.innerHTML = this.splitTemplate.elementRef.nativeElement;
  }

  splitHorizontal(objHorz) {
    console.log(objHorz);
    this.layoutLstVert.forEach(lay => {
      if (lay.idVert === objHorz.idVert) {
        lay.childArr.push({ idHorz: this.counterHorz });
        this.increaseHorz();
      }
    });
    console.log(this.layoutLstVert);
  }

  onFileChanged(event) {
    const file = event.target.files[0];
    console.log(file);
  }

  submit() {
    this.layoutLstVert.forEach(lay => {
      lay.childArr.forEach((chld: IChild) => {
        const domId = 'chld-' + lay.idVert + '-' + chld.idHorz;
        chld.domId = domId;
        const domElm = document.getElementById(domId);
        chld.startX = domElm.getBoundingClientRect().top;
        chld.startY = domElm.getBoundingClientRect().left;
        chld.width = domElm.getBoundingClientRect().width;
        chld.height = domElm.getBoundingClientRect().height;
        // console.log(chld);
      });
    });
    const data = { childArr: this.layoutLstVert, height: window.screen.height, width: window.screen.width }
    this.appService.saveNewLayout(data).subscribe(res => {
      console.log(res);
    });

    console.log(JSON.stringify(this.layoutLstVert))
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
