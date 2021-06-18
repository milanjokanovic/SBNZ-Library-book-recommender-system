import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Icons } from 'src/app/enums/icons.enum';
import { TableHeader } from '../../gen-table/table-header';
import { TableOperation } from '../../gen-table/table-operation';
import { Book } from '../../model/book';
import { ReadbookComponent } from '../../readbook/readbook.component';
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

  constructor(private service: BookService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.loadData();
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
    //this.modalService.open(book, {ariaLabelledBy: 'modal-basic-title', size: 'lg', scrollable: true});
    const modalRef = this.modalService.open(ReadbookComponent,
      { ariaLabelledBy: 'read-book', size: 'lg', scrollable: true });
   modalRef.componentInstance.book = book;
   modalRef.componentInstance.refresh = () => { this.loadData(); };
  }

}
