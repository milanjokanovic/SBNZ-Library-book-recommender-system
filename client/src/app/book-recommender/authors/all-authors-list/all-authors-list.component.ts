import { Component, OnInit } from '@angular/core';
import { faHome } from '@fortawesome/free-solid-svg-icons';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Icons } from 'src/app/enums/icons.enum';
import { TableHeader } from '../../gen-table/table-header';
import { TableOperation } from '../../gen-table/table-operation';
import { Author } from '../../model/author';
import { Favorite } from '../../model/favorite';
import { AuthService } from '../../services/auth.service';
import { AuthorService } from '../../services/author.service';
import { AddAuthorComponent } from '../add-author/add-author.component';

@Component({
  selector: 'app-all-authors-list',
  templateUrl: './all-authors-list.component.html',
  styleUrls: ['./all-authors-list.component.sass']
})
export class AllAuthorsListComponent implements OnInit {

  authorList: Author[] = [];
  faHomeIcon = faHome;

  tableHeader: TableHeader[] = [
    {
      headerName: 'Name',
      fieldName: ['name']
    }
  ];

  operations: TableOperation<Author>[] = [
    
  ];

  constructor(private service: AuthorService, private authService: AuthService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.loadData();
  }

  loadData(){
    this.service.getAllAuthors().subscribe(
      res => {
        this.authorList = res.body as Author[];
      }

    );
  }

  add(){
    const modalRef = this.modalService.open(AddAuthorComponent, { ariaLabelledBy: 'add-author', size: 'lg', scrollable: true });
    modalRef.componentInstance.refresh = () => { this.loadData(); };
  
  }

}
