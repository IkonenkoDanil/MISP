# Generated by Django 4.1.5 on 2023-01-23 13:43

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('main', '0003_alter_note_day_alter_note_time'),
    ]

    operations = [
        migrations.AddConstraint(
            model_name='daystats',
            constraint=models.UniqueConstraint(fields=('user', 'date'), name='user_date_unique'),
        ),
    ]
