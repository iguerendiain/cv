import { Component,Input,OnInit } from '@angular/core';
import { DataSubscribedComponent } from './datasubscribedcomponent';
import { DomSanitizer,SafeStyle } from '@angular/platform-browser';
import { DataService } from './data.service';

@Component({
  selector: 'portfolio-item',
  templateUrl: './portfolioitem.component.html',
  styleUrls: ['./portfolioitem.component.css']
})

export class PortfolioItemComponent extends DataSubscribedComponent {
  @Input() project:any;

  title:string;
  banner:SafeStyle;
  icon:string;
  description:string;
  items:string[];
  urls:any[];

  constructor(private domSanitizer:DomSanitizer,private dataToSuper:DataService){
    super(dataToSuper);
  }

  ngOnInit():void{
    this.updateData();
  }

  onLanguageChanged(language:string):void{
    this.updateData();
  }

  onCVDataChanged(cvData:any):void{
    this.updateData();
  }

  updateData():void{
    let lang = this.getLanguage();

    this.banner = this.domSanitizer.bypassSecurityTrustStyle("url('"+this.project.icon+"')");
    this.icon = this.project.icon;
    this.title = this.project.title[lang];
    this.description = this.project.description[lang];

    let rawItems:any[] = this.project.items;
    if (rawItems!=null && rawItems.length>0){
      this.items = [];
      for (let rawItem of rawItems){
        this.items.push(rawItem.title[lang]);
      }
    }else{
      this.items = null;
    }

    let rawDesription:any[] = this.project.description;
    if (rawDesription!=null && this.items==null){
      this.description = rawDesription[lang];
    }

    let rawURLs:any[] = this.project.urls;
    if (rawURLs!=null && rawURLs.length > 0){
      this.urls = [];
      for (let rawURL of rawURLs){
        this.urls.push({
          icon:rawURL.icon,
          title:rawURL.title[lang],
          url:rawURL.url
        });
      }
    }
  }
}