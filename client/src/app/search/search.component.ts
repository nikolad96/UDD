import { Component, OnInit, Query } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Rad } from '../model/Rad';
import { QueryDTO } from '../model/QueryDTO';
import { SearchService } from '../services/search.service';
import { MultiFieldQueryDTO } from '../model/MultiFieldQueryDTO';
import { MoreLikeThisDTO } from '../model/MoreLikeThisDTO';
import { RadDTO } from '../model/RadDTO';
import { Recenzent } from '../model/Recenzent';


@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  form: FormGroup;
  form1: FormGroup;
  form2: FormGroup;
  form3: FormGroup;
  form4: FormGroup;

  recenzents: Recenzent[];
  radDTO: RadDTO;
  rads : Rad[];
  allRads: Rad[];
  query: QueryDTO;
  more: MoreLikeThisDTO;
  flag: string;
  multiQuery: MultiFieldQueryDTO;


  constructor(private http: HttpClient, private searchService: SearchService) {
    this.flag = 'search';
    this.form = new FormGroup({
      vrednost: new FormControl('', Validators.required  ),
      polje: new FormControl('', Validators.required  ),
    });
    this.form1 = new FormGroup({
      Naziv: new FormControl('', Validators.required  ),
      Oblast: new FormControl('', Validators.required  ),
      Abstrakt: new FormControl('', Validators.required  ),
      Autor: new FormControl('', Validators.required  ),
      Kljucne: new FormControl('', Validators.required  ),
      tip: new FormControl('', Validators.required  ),
    });
    this.form2 = new FormGroup({
      vrednost: new FormControl('', Validators.required  ),
     
    });

    this.form3 = new FormGroup({
      vrednost: new FormControl('', Validators.required  ),
      min: new FormControl('', Validators.required  ),
      max: new FormControl('', Validators.required  ),
     
    });
    this.form4 = new FormGroup({
      rad: new FormControl('', Validators.required  ),

     
    });
  }
  ngOnInit() {
    this.searchService.getRadovi().subscribe(
      data => {
       this.allRads = data;
       console.log(this.rads);
      }, error => {
        alert("error uploading file");
      }
    );
  }

  multiField(){
    this.flag = "multiSearch"
  }
  geo(){
    this.flag = "geoSearch"
  }
  moreLikeThis(){
    this.flag = "moreLikeThis"
  }
  normal(){
    this.flag = "search"
  }
  content(){
    this.flag = "content"
  }
  onSubmit() {
    this.query = new QueryDTO();
    this.query.polje = this.form.get('polje').value;
    this.query.vrednost = this.form.get('vrednost').value;
    console.log(this.query);
    this.searchService.postSearch1(this.query).subscribe(data => {
      this.rads = data;

      console.log(this.rads);
    },
    error => {
      console.log(error);

    });
  }
  onSubmitGeo() {
   this.radDTO = new RadDTO();
    this.radDTO.id = this.form4.get('rad').value;
    console.log(this.query);
    this.searchService.postSearchGeo(this.radDTO).subscribe(data => {
      this.recenzents = data;
      console.log(this.recenzents);
    },
    error => {
      console.log(error);

    });
  }
  onSubmitContent() {
    this.query = new QueryDTO();
    this.query.polje = 'text';
    this.query.vrednost = this.form2.get('vrednost').value;
    console.log(this.query);
    this.searchService.postSearch1(this.query).subscribe(data => {
      this.rads = data;

      console.log(this.rads);
    },
    error => {
      console.log(error);

    });
  }
  onSubmitMore() {
    this.more = new MoreLikeThisDTO();
    this.more.vrednost = this.form3.get('vrednost').value;
    this.more.min = this.form3.get('min').value;
    this.more.max = this.form3.get('max').value;
    console.log(this.more);
    this.searchService.postSearchMore(this.more).subscribe(data => {
      this.rads = data;

      console.log(this.rads);
    },
    error => {
      console.log(error);

    });
  }

  onSubmitMulti() {
    this.multiQuery = new MultiFieldQueryDTO();
    this.query = new QueryDTO();
    this.query.polje = 'naziv';
    this.query.vrednost = this.form1.get('Naziv').value;
    this.multiQuery.queryDTOList = [];
    this.multiQuery.queryDTOList.push(this.query);

    this.query = new QueryDTO();
    this.query.polje = 'oblast';
    this.query.vrednost = this.form1.get('Oblast').value;
    this.multiQuery.queryDTOList.push(this.query);
  
    this.query = new QueryDTO();
    this.query.polje = 'abstrakt';
    this.query.vrednost = this.form1.get('Abstrakt').value;
    this.multiQuery.queryDTOList.push(this.query);

    this.query = new QueryDTO();
    this.query.polje = 'autor';
    this.query.vrednost = this.form1.get('Autor').value;
    this.multiQuery.queryDTOList.push(this.query);

    this.query = new QueryDTO();
    this.query.polje = 'kljucne';
    this.query.vrednost = this.form1.get('Kljucne').value;
    this.multiQuery.queryDTOList.push(this.query);

    if(this.form1.get('tip').value == ''){
      this.multiQuery.tip = 'and';
      console.log("if");
    }else{
      this.multiQuery.tip = this.form1.get('tip').value;
      console.log("else");
    }
    

    console.log(this.multiQuery);
    this.searchService.postSearchMulti(this.multiQuery).subscribe(data => {
      this.rads = data;

      console.log(this.rads);
    },
    error => {
      console.log(error);

    });
  }

}
