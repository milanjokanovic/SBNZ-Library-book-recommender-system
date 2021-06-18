import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Book } from '../model/book';
import { Read } from '../model/read';
import { Score } from '../model/score';
import { AuthService } from '../services/auth.service';
import { BookService } from '../services/book.service';
import { ScoreService } from '../services/score.service';

@Component({
  selector: 'app-add-grade-book',
  templateUrl: './add-grade-book.component.html',
  styleUrls: ['./add-grade-book.component.sass']
})
export class AddGradeBookComponent implements OnInit {

  @Input() refresh;
  @Input() book: Book;
  score: Score= {userId: 1, bookTitle: "title", value: 0}
  badRequest = false;


  constructor(public activeModal: NgbActiveModal,
    private scoreService: ScoreService, private authService: AuthService) { }

  ngOnInit(): void {
  }

  send(){
    this.score.bookTitle = this.book.title;
    this.score.userId = this.authService.getUserId();

    this.scoreService.score(this.score).subscribe(
      res => {

        this.refresh();
        this.activeModal.close();
      },
      error => {
        if (error.status === 400) {
          this.badRequest = true;
        }
      }

    );
  }

}
