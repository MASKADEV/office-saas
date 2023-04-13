import {Component, OnDestroy} from '@angular/core';
import {ConverterService} from "../../../../service/converter.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-doc-to-pdf',
  templateUrl: './doc-to-pdf.component.html',
  styleUrls: ['./doc-to-pdf.component.scss']
})
export class DocToPdfComponent implements OnDestroy{
  constructor(private convertService : ConverterService) {
  }
  private convertSub : Subscription = new Subscription();
  formData = new FormData();
  loading : boolean = false;

  handleFileInput(event : any) {
    const file: File = event.target.files[0];
    this.formData.append('file', file)
  }
  convertFile() {
    try {
      // console.log(this.formData.get('file'));
      this.loading = true;
      this.convertService.docToPdf(this.formData).subscribe(
        (res: any) => {
          const blob = new Blob([res.body], { type: 'application/pdf' });
          const url = window.URL.createObjectURL(blob);
          window.open(url);
        },
        error => console.error(error),
        () => this.loading = false
      )
    }catch (e) {

    }finally {
      this.loading = false
    }
  }

  reset() {
  }

  ngOnDestroy() {
    this.convertSub.unsubscribe();
  }
}
