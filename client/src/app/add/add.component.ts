import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/users/user.service';
import { RepositoryService } from '../services/repository/repository.service';
import { Router } from '@angular/router';
import { SearchService } from '../services/search.service';
import { Rad } from '../model/Rad';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {

  form: FormGroup;
  fileUrl: string;
  fileToUpload: File;
  radId: any;
  fileName: any;
  rads : Rad[];
  constructor(private userService : UserService, private searchService : SearchService,private router: Router) { 
    this.form = new FormGroup({
      rad: new FormControl('', Validators.required  ),
    });
  }

  ngOnInit() {
    this.searchService.getRadovi().subscribe(
      data => {
       this.rads = data;
       console.log(this.rads);
      }, error => {
        alert("error uploading file");
      }
    );
  }

  handleFileInput(file:FileList){
    this.fileToUpload =file.item(0);
    var reader = new FileReader();
    reader.onload=(event:any)=>{
      this.fileUrl =event.target.result;
    }
    reader.readAsDataURL(this.fileToUpload);
    console.log("URL "+this.fileUrl);
    console.log("file "+this.fileToUpload);
  }
   onSubmit(){
          const formData = new FormData();  
          formData.append("file", this.fileToUpload);
          console.log(this.form.get('rad').value)
          this.radId = this.form.get('rad').value;
          this.searchService.postFile(this.radId, formData).subscribe(
            data => {
              this.router.navigate(["/search"]);
            }, error => {
              alert("error uploading file");
            }
          );
       
  }

}
