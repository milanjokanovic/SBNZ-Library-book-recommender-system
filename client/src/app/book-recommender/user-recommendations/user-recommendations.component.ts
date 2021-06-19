import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Icons } from 'src/app/enums/icons.enum';
import { TableHeader } from '../gen-table/table-header';
import { TableOperation } from '../gen-table/table-operation';
import { Book } from '../model/book';
import { ReadbookComponent } from '../readbook/readbook.component';
import { AuthService } from '../services/auth.service';
import { BookService } from '../services/book.service';

@Component({
  selector: 'app-user-recommendations',
  templateUrl: './user-recommendations.component.html',
  styleUrls: ['./user-recommendations.component.sass']
})
export class UserRecommendationsComponent implements OnInit {
  bookList: Book[] = [];

  tableHeader: TableHeader[] = [
    {
      headerName: 'Title',
      fieldName: ['title']
    },
    {
      headerName: 'Page Number',
      fieldName: ['pageNum']
    },
    {
      headerName: 'View Number',
      fieldName: ['viewNumber']
    },
    {
      headerName: 'Author Name',
      fieldName: ['authorName']
    },
    {
      headerName: 'Year of Publishing',
      fieldName: ['yearOfPublishing']
    },
    {
      headerName: 'Genres',
      fieldName: ['genres']
    }
  ];

  operations: TableOperation<Book>[] = [
    /*{
      operation: (element) => this.read(element),
      icon: Icons.read
    }*/
  ];

  
  constructor(private service: BookService, private auth_service: AuthService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.loadData();
  }

  loadData(){
    this.service.getUserRecBooks(this.auth_service.getUserId()).subscribe(
      res => {
        this.bookList = res.body as Book[];
      }

    );
  }

}
