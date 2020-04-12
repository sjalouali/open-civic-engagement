import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { ActionServiceOceDeleteDialogComponent } from 'app/entities/action-service-oce/action-service-oce-delete-dialog.component';
import { ActionServiceOceService } from 'app/entities/action-service-oce/action-service-oce.service';

describe('Component Tests', () => {
  describe('ActionServiceOce Management Delete Component', () => {
    let comp: ActionServiceOceDeleteDialogComponent;
    let fixture: ComponentFixture<ActionServiceOceDeleteDialogComponent>;
    let service: ActionServiceOceService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterTestModule],
        declarations: [ActionServiceOceDeleteDialogComponent]
      })
        .overrideTemplate(ActionServiceOceDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ActionServiceOceDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ActionServiceOceService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
