import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Icons } from 'src/app/enums/icons.enum';
import { AddBookComponent } from '../../add-book/add-book.component';
import { TableHeader } from '../../gen-table/table-header';
import { TableOperation } from '../../gen-table/table-operation';
import { Book } from '../../model/book';
import { ReadbookComponent } from '../../readbook/readbook.component';
import { AuthService } from '../../services/auth.service';
import { BookService } from '../../services/book.service';

@Component({
  selector: 'app-allbooks',
  templateUrl: './allbooks.component.html',
  styleUrls: ['./allbooks.component.sass']
})
export class AllbooksComponent implements OnInit {
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
    {
      operation: (element) => this.read(element),
      icon: Icons.read
    }
  ];
  authority: string;

  constructor(private service: BookService, private modalService: NgbModal, private authService: AuthService) { }

  ngOnInit(): void {
    this.loadData();
    this.authority = this.authService.getRole();
  }

  loadData(){
    this.service.getAllBooks().subscribe(
      res => {
        this.bookList = res.body as Book[];
      }

    );
  }

  read(book) {
    console.log(book.id);
    if(this.authService.getRole() === "ROLE_GUEST"){

    
      //this.modalService.open(book, {ariaLabelledBy: 'modal-basic-title', size: 'lg', scrollable: true});
      const modalRef = this.modalService.open(ReadbookComponent,
        { ariaLabelledBy: 'read-book', size: 'lg', scrollable: true });
      modalRef.componentInstance.book = book;
      modalRef.componentInstance.refresh = () => { this.loadData(); };
    }
  }

  add(){
    const modalRef = this.modalService.open(AddBookComponent, { ariaLabelledBy: 'add-book', size: 'lg', scrollable: true });
    modalRef.componentInstance.refresh = () => { this.loadData(); };
  }

}
