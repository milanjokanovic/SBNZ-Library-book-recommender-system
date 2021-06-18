import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Book } from '../model/book';
import { Read } from '../model/read';
import { AuthService } from '../services/auth.service';
import { BookService } from '../services/book.service';

@Component({
  selector: 'app-readbook',
  templateUrl: './readbook.component.html',
  styleUrls: ['./readbook.component.sass']
})
export class ReadbookComponent implements OnInit {

  @Input() refresh;
  @Input() book: Book;

  read:Read ={ bookId: 1, userId: 1};

  constructor(public activeModal: NgbActiveModal,
    private bookService: BookService, private authService: AuthService) { }

  ngOnInit(): void {
  }

  send(){
    this.read.bookId = this.book.id;
    this.read.userId = this.authService.getUserId();
    console.log(this.read.bookId);
    this.bookService.read(this.read).subscribe(
      res => {

        this.refresh();
        this.activeModal.close();
      },
      error => {
        
      }

    );
  }

}
