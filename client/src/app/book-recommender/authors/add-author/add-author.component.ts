import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Author } from '../../model/author';
import { AuthorService } from '../../services/author.service';

@Component({
  selector: 'app-add-author',
  templateUrl: './add-author.component.html',
  styleUrls: ['./add-author.component.sass']
})
export class AddAuthorComponent implements OnInit {

  @Input() refresh;
  author: Author={systemGrade: 0, name: "", favored: false};
  notFound = false;
  badRequest = false;
  unauthorized = false;
  constructor(public activeModal: NgbActiveModal,
              private service: AuthorService) { }

  ngOnInit(): void {
  }

  add() {
    this.service.create(this.author).subscribe(
      res => {
        this.refresh();
        this.activeModal.close();
        this.badRequest = false;
      },
      error => {
        if (error.status === 400) {
          this.badRequest = true;
        }
      }

    );
  }

}
