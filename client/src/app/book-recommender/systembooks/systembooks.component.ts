import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Icons } from 'src/app/enums/icons.enum';
import { TableHeader } from '../gen-table/table-header';
import { TableOperation } from '../gen-table/table-operation';
import { Book } from '../model/book';
import { ReadbookComponent } from '../readbook/readbook.component';
import { BookService } from '../services/book.service';
import {
  faHome
} from '@fortawesome/free-solid-svg-icons';
import { AddFavoriteAuthorComponent } from '../add-favorite-author/add-favorite-author.component';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-systembooks',
  templateUrl: './systembooks.component.html',
  styleUrls: ['./systembooks.component.sass']
})
export class SystembooksComponent implements OnInit {

  bookList: Book[] = [];
  faHomeIcon = faHome;

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
    {
      operation: (element) => this.read(element),
      icon: Icons.read
    }
  ];

  constructor(private service: BookService, private modalService: NgbModal, private authService: AuthService) { }

  ngOnInit(): void {
    this.loadData();
  }

  loadData(){
    this.service.getSystemBooks().subscribe(
      res => {
        this.bookList = res.body as Book[];
      }

    );
  }

  read(book) {
    if(this.authService.getRole() === "ROLE_GUEST"){
      console.log(book.id);
      //this.modalService.open(book, {ariaLabelledBy: 'modal-basic-title', size: 'lg', scrollable: true});
      const modalRef = this.modalService.open(ReadbookComponent,
        { ariaLabelledBy: 'read-book', size: 'lg', scrollable: true });
      modalRef.componentInstance.book = book;
      modalRef.componentInstance.refresh = () => { this.loadData(); };
    }
  }

}
